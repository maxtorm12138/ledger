package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.dao.AccountBalanceRepository;
import org.maxtorm.ledger.mapper.AccountBalanceMapper;
import org.maxtorm.ledger.mapper.AccountMapper;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.proto.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private AccountBalanceRepository accountBalanceRepository;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Entity.Account open(Entity.Account account) {
        var accountToCreate = AccountMapper.INSTANCE.Convert(account);
        accountToCreate.setAccountId(UUID.randomUUID().toString());

        // check arguments
        if (!accountToCreate.getRootAccountId().isEmpty() && !accountRepository.existsAccountPoByAccountId(accountToCreate.getRootAccountId())) {
            throw new IllegalArgumentException(MessageFormatter.format("rootAccountId: {} not exists", accountToCreate.getRootAccountId()).getMessage());
        }

        if (!accountToCreate.getParentAccountId().isEmpty() && !accountRepository.existsAccountPoByAccountId(accountToCreate.getParentAccountId())) {
            throw new IllegalArgumentException(MessageFormatter.format("parentAccountId: {} not exists", accountToCreate.getParentAccountId()).getMessage());
        }


        // initialize some well-known commodity summary
        var accountCreated = AccountMapper.INSTANCE.Convert(accountRepository.save(accountToCreate));
        logger.trace("accountCreated: {}", accountCreated);
        return accountCreated;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Entity.AccountBalance addAccountBalance(Entity.AccountBalance balance, Long difference) {
        var paramBalancePo = AccountBalanceMapper.INSTANCE.Convert(balance);
        paramBalancePo.setAmount(difference);

        // search for balance entity
        var databaseBalancePo = accountBalanceRepository.getAccountBalancePoByAccountIdAndCommodity(paramBalancePo.getAccountId(), paramBalancePo.getCommodity());

        AtomicReference<AccountBalancePo> updatedBalancePo = new AtomicReference<>();
        databaseBalancePo.ifPresentOrElse(balancePo -> {
            balancePo.setAmount(balancePo.getAmount() + difference);
            updatedBalancePo.set(accountBalanceRepository.save(balancePo));
        }, () -> updatedBalancePo.set(accountBalanceRepository.save(paramBalancePo)));

        return AccountBalanceMapper.INSTANCE.Convert(updatedBalancePo.get());
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<Entity.AccountTree> tree(String accountId) {
        var accountTreeList = new ArrayList<Entity.AccountTree>();

        List<AccountPo> accountInLevel;
        if (accountId.isEmpty()) {
            accountInLevel = accountRepository.findAccountPoByRootAccountIdIs("");
        } else {
            accountInLevel = accountRepository.findAccountPoByParentAccountId(accountId);
        }

        accountInLevel.forEach(accountPo -> {
            var accountTreeBuilder = Entity.AccountTree.newBuilder();
            accountTreeBuilder.setSelf(AccountMapper.INSTANCE.Convert(accountPo));
            accountTreeBuilder.addAllChild(tree(accountPo.getAccountId()));
            accountTreeList.add(accountTreeBuilder.build());
        });

        return accountTreeList;
    }
}
