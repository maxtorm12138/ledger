package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.maxtorm.ledger.obj.Account;
import org.maxtorm.ledger.dao.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(FundService.class);

    private AccountRepository accountRepository;


    @Transactional(value = SUPPORTS)
    public Account create(Account account) {
        account.setAccountId(UUID.randomUUID().toString());
        if (account.getParentAccountId().isEmpty()) {
            account.setParentAccountId(account.getAccountId());
        }

        if (account.getRootAccountId().isEmpty()) {
            account.setRootAccountId(account.getAccountId());
        }

        account = accountRepository.save(account);
        return account;
    }
}
