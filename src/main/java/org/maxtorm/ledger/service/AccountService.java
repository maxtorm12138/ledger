package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.entity.account.*;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.exception.OpenAccountAlreadyExistException;
import org.maxtorm.ledger.entity.account.AccountBalanceRepository;
import org.maxtorm.ledger.entity.account.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private AccountBalanceRepository accountBalanceRepository;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<Account> getAccountWithBalance(String accountId) {
        var accountPo = accountRepository.getAccountPoByAccountId(accountId);
        if (accountPo.isEmpty()) {
            return Optional.empty();
        }

        var account = AccountMapper.INSTANCE.convert(accountPo.get());
        var accountBalancePoList = accountBalanceRepository.getAccountBalancePosByAccountId(accountId);
        account.setAccountBalance(AccountBalanceMapper.INSTANCE.convertPosToBos(accountBalancePoList));

        return Optional.of(account);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<AccountBalance> getAccountBalance(String accountId, Commodity commodity) {
        var accountBalancePo = accountBalanceRepository.getAccountBalancePoByAccountIdAndCommodity(accountId, commodity);
        return accountBalancePo.map(AccountBalanceMapper.INSTANCE::convert);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void saveAccountBalance(AccountBalance accountBalance) {
        var accountBalancePo = AccountBalanceMapper.INSTANCE.convert(accountBalance);
        accountBalancePo.setAccountBalanceId("%s|%s".formatted(accountBalance.getAccountId(), accountBalance.getCommodity().getQualifiedName()));

        accountBalancePo = accountBalanceRepository.saveAndFlush(accountBalancePo);
        AccountBalanceMapper.INSTANCE.convert(accountBalancePo);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<AccountTree> tree(String parentAccountId) {
        Function<String, List<Account>> findAccountWithBalanceByParentAccountId = (pAccountId) -> {
            var accountPoList = accountRepository.findAccountPosByParentAccountId(parentAccountId);
            if (accountPoList.isEmpty()) {
                return List.of();
            }

            var accountList = AccountMapper.INSTANCE.convertPosToBos(accountPoList);

            for (Account account : accountList) {
                var accountBalancePoList = accountBalanceRepository.findAccountBalancePosByAccountId(account.getAccountId());
                account.setAccountBalance(AccountBalanceMapper.INSTANCE.convertPosToBos(accountBalancePoList));
            }

            return accountList;
        };


        List<AccountTree> accountTreeList = new ArrayList<>();
        List<Account> accountList = findAccountWithBalanceByParentAccountId.apply(parentAccountId);
        accountList.forEach(account -> {
            AccountTree accountTree = AccountTreeMapper.INSTANCE.convert(account);
            accountTree.setChildren(tree(account.getAccountId()));
            accountTreeList.add(accountTree);
        });

        return accountTreeList;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void open(Account account) {
        try {
            var accountPo = AccountMapper.INSTANCE.convert(account);
            accountRepository.saveAndFlush(accountPo);

            AccountBalancePo accountBalancePo = new AccountBalancePo();
            accountBalancePo.setAccountBalanceId(String.format("%s|%s", accountPo.getAccountId(), accountPo.getMajorCommodity().getQualifiedName()));
            accountBalancePo.setAccountId(accountPo.getAccountId());
            accountBalancePo.setCommodity(accountPo.getMajorCommodity());
            accountBalancePo.setBookBalance(BigDecimal.ZERO);
            accountBalancePo.setTotalInflow(BigDecimal.ZERO);
            accountBalancePo.setTotalOutflow(BigDecimal.ZERO);

            accountBalanceRepository.saveAndFlush(accountBalancePo);

        } catch (DataIntegrityViolationException e) {
            throw new OpenAccountAlreadyExistException(MessageFormatter.format("{} already exists", account.getName()).getMessage());
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void openBalance(String accountId, Commodity commodity) {
        // lock account
        var account = getAccountWithBalance(accountId).orElseThrow();

        // search existence
        boolean exists = false;
        for (var accountBalance: account.getAccountBalance()) {
            if (accountBalance.getCommodity().equals(commodity)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            return;
        }

        AccountBalancePo accountBalancePo = new AccountBalancePo();
        accountBalancePo.setAccountBalanceId(String.format("%s|%s", accountId, commodity.getQualifiedName()));
        accountBalancePo.setAccountId(accountId);
        accountBalancePo.setCommodity(commodity);
        accountBalancePo.setBookBalance(BigDecimal.ZERO);
        accountBalancePo.setTotalInflow(BigDecimal.ZERO);
        accountBalancePo.setTotalOutflow(BigDecimal.ZERO);

        accountBalanceRepository.save(accountBalancePo);
    }

    private List<AccountPo> pathImpl(String childAccountId, String fatherAccountId) {
        var start = new AccountPo();
        start.setParentAccountId(childAccountId);

        var end = accountRepository.getAccountPoByAccountId(fatherAccountId).orElseThrow();
        int maxWalks = 4;

        List<AccountPo> accountList = new ArrayList<>();
        while (maxWalks > 0 && !Objects.equals(start.getAccountId(), end.getAccountId())) {
            start = accountRepository.getAccountPoByAccountId(start.getParentAccountId()).orElseThrow();
            accountList.add(start);
            maxWalks--;
        }

        if (maxWalks <= 0) {
            throw new IndexOutOfBoundsException();
        }

        return accountList;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<Account> path(String childAccountId, String fatherAccountId) {
        var accountPoList = pathImpl(childAccountId, fatherAccountId);
        return AccountMapper.INSTANCE.convertPosToBos(accountPoList);
    }

}
