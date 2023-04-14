package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.po.AccountMapper;
import org.maxtorm.ledger.po.AccountPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
@Transactional(value = Transactional.TxType.SUPPORTS)
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;

    public Optional<Api.Account> findByAccountId(String accountId) {
        return accountRepository.findAccountPoByAccountId(accountId).map(AccountMapper.INSTANCE::Convert);
    }

    public Optional<Api.Account> getByAccountId(String accountId) {
        return accountRepository.getAccountPoByAccountId(accountId).map(AccountMapper.INSTANCE::Convert);
    }

    public Api.Account create(Api.Account account, Optional<Api.Account> rootAccount, Optional<Api.Account> parentAccount) {

        String accountId = UUID.randomUUID().toString();
        AccountPo accountToCreate = AccountMapper.INSTANCE.Convert(account);
        logger.trace("accountToCreate: {}", accountToCreate);
        accountToCreate.setAccountId(accountId);
        rootAccount.ifPresentOrElse(root -> accountToCreate.setRootAccountId(root.getRootAccountId()), () -> accountToCreate.setRootAccountId(accountId));
        parentAccount.ifPresentOrElse(parent -> accountToCreate.setParentAccountId(parent.getParentAccountId()), () -> accountToCreate.setParentAccountId(accountId));

        AccountPo accountCreated = accountRepository.save(accountToCreate);
        logger.trace("accountCreated: {}", accountCreated);
        return AccountMapper.INSTANCE.Convert(accountCreated);
    }
}
