package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.dao.AccountBalanceInsertRepository;
import org.maxtorm.ledger.dao.AccountBalanceRepository;
import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.mapper.AccountBalanceMapper;
import org.maxtorm.ledger.mapper.AccountMapper;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.po.AccountPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private AccountBalanceRepository accountBalanceRepository;
    private AccountBalanceInsertRepository accountBalanceInsertRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(value = Transactional.TxType.REQUIRED)
    public void initialize() {
        if (accountRepository.existsAccountPoByName("system_root")) {
            logger.info("Ledger is initialized");
            return;
        }

        // system root account, balances should always be zero
        AccountPo system_root = new AccountPo();
        system_root.setAccountId("system_root");
        system_root.setName("system_root");
        system_root = accountRepository.save(system_root);

        // initialize root account
        AccountPo user_root = new AccountPo();
        user_root.setAccountId("user_root");
        user_root.setName("user_root");
        user_root.setParentAccountId(system_root.getAccountId());
        accountRepository.save(user_root);

        // initialize equity account
        AccountPo equity = new AccountPo();
        equity.setAccountId("equity");
        equity.setName("equity");
        equity.setParentAccountId(system_root.getAccountId());
        accountRepository.save(equity);

        logger.info("Ledger is initialized");
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Account open(Account account) {
        var accountPo = AccountMapper.INSTANCE.convert(account);
        var accountBalancePos = AccountBalanceMapper.INSTANCE.convertBosToPos(account.getAccountBalance());

        accountRepository.getAccountPoByAccountId(accountPo.getParentAccountId()).orElseThrow();
        accountPo.setAccountId(UUID.randomUUID().toString());

        var accountPoCreated = accountRepository.save(accountPo);

        for (var accountBalancePo : accountBalancePos) {
            accountBalancePo.setAccountId(accountPoCreated.getAccountId());
        }


        return AccountMapper.INSTANCE.convert(accountPoCreated);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void transfer(String accountId, Commodity commodity, BigDecimal amount) {
        // find user_root
        var userRootAccountPo = accountRepository.getAccountPoByName("user_root").orElseThrow();

        var start = new AccountPo();
        start.setParentAccountId(accountId);

        List<String> path = new ArrayList<>();
        int maxNodePermit = 4;

        while (maxNodePermit > 0 && !start.getAccountId().equals(userRootAccountPo.getAccountId())) {
            start = accountRepository.findAccountPoByAccountId(start.getParentAccountId()).orElseThrow();
            path.add(start.getAccountId());
            maxNodePermit--;
        }

        if (maxNodePermit < 0) {
            throw new IllegalArgumentException("max depth account");
        }

        path.forEach(pathAccountId -> {
            AccountBalancePo accountBalancePo = new AccountBalancePo();
            accountBalancePo.setAccountId(pathAccountId);
            accountBalancePo.setCommodity(commodity);
            accountBalanceInsertRepository.addBalance(accountBalancePo, amount);
        });
    }
}
