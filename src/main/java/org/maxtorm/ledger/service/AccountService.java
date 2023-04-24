package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountTree;
import org.maxtorm.ledger.mapper.AccountBalanceMapper;
import org.maxtorm.ledger.mapper.AccountMapper;
import org.maxtorm.ledger.mapper.AccountTreeMapper;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.repository.AccountBalanceRepository;
import org.maxtorm.ledger.repository.AccountRepository;
import org.maxtorm.ledger.repository.EntityInsertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private EntityInsertRepository entityInsertRepository;

    private AccountRepository accountRepository;
    private AccountBalanceRepository accountBalanceRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(value = Transactional.TxType.REQUIRED)
    public void initialize() {
        if (accountRepository.getAccountPoByAccountId("system_root").isPresent()) {
        }

        // system root account, balances should always be zero
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<Account> getAccountByName(String name) {
        var accountPo = accountRepository.getAccountPoByName(name);
        return accountPo.map(AccountMapper.INSTANCE::convert);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<Account> getAccount(String accountId) {
        var accountPo = accountRepository.getAccountPoByAccountId(accountId);
        return accountPo.map(AccountMapper.INSTANCE::convert);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Optional<Account> getAccountWithBalanceByName(String name) {
        var accountPo = accountRepository.getAccountPoByName(name);
        if (accountPo.isEmpty()) {
            return Optional.empty();
        }

        var account = AccountMapper.INSTANCE.convert(accountPo.get());

        var accountBalancePoList = accountBalanceRepository.getAccountBalancePos(account.getAccountId());
        account.setAccountBalance(AccountBalanceMapper.INSTANCE.convertPosToBos(accountBalancePoList));

        return Optional.of(account);
    }

    public List<AccountTree> tree(String parentAccountId) {
        Function<String, List<Account>> findAccountWithBalanceByParentAccountId = (pAccountId) -> {
            var accountPoList = accountRepository.findAccountPosByParentAccountId(parentAccountId);
            if (accountPoList.isEmpty()) {
                return List.of();
            }

            var accountList = AccountMapper.INSTANCE.convertPosToBos(accountPoList);
            accountList.forEach(account -> {
                var accountBalancePoList = accountBalanceRepository.findAccountBalancePos(account.getAccountId());
                account.setAccountBalance(AccountBalanceMapper.INSTANCE.convertPosToBos(accountBalancePoList));
            });

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
    public Account open(Account account) {
        var accountPo = AccountMapper.INSTANCE.convert(account);
        if (account.getAccountId().isEmpty()) {
            String accountId = UUID.randomUUID().toString();
            accountPo.setAccountId(accountId);
        }

        entityInsertRepository.insertAccount(accountPo);

        AccountBalancePo accountBalancePo = new AccountBalancePo();
        accountBalancePo.setAccountId(accountPo.getAccountId());
        accountBalancePo.setCommodity(accountPo.getMajorCommodity());
        accountBalancePo.setBookBalance(BigDecimal.ZERO);
        entityInsertRepository.insertAccountBalance(accountBalancePo);

        return AccountMapper.INSTANCE.convert(accountPo);
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
