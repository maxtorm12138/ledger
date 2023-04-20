package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountBalance;
import org.maxtorm.ledger.bo.AccountTree;

import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.dao.AccountBalanceRepository;

import org.maxtorm.ledger.mapper.AccountBalanceMapper;
import org.maxtorm.ledger.mapper.AccountMapper;

import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.po.AccountPo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private AccountBalanceRepository accountBalanceRepository;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Account open(Account account) {
        var accountPo = AccountMapper.INSTANCE.Convert(account);

        if (!accountPo.getParentAccountId().isEmpty()) {
            accountRepository.getAccountPoByAccountId(accountPo.getParentAccountId()).orElseThrow( () -> new IllegalArgumentException(MessageFormatter.format("parentAccount: {} not exists", accountPo.getParentAccountId()).getMessage()));
        }

        if (!accountPo.getRootAccountId().isEmpty()) {
            accountRepository.getAccountPoByAccountId(account.getRootAccountId()).orElseThrow(() -> new IllegalArgumentException(MessageFormatter.format("rootAccount: {} not exists", accountPo.getRootAccountId()).getMessage()));
        }

        var accountPoCreated = accountRepository.save(accountPo);
        return AccountMapper.INSTANCE.Convert(accountPoCreated);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<AccountTree> tree(String accountId) {
        var accountTreeList = new ArrayList<AccountTree>();

        List<AccountPo> accountInLevel;
        if (accountId.isEmpty()) {
            accountInLevel = accountRepository.findAccountPoByRootAccountIdIs("");
        } else {
            accountInLevel = accountRepository.findAccountPoByParentAccountId(accountId);
        }

        accountInLevel.forEach(accountPo -> {
            List<AccountBalancePo> accountBalancePo = accountBalanceRepository.findAccountBalancePosByAccountId(accountPo.getAccountId());

            Account account = AccountMapper.INSTANCE.Convert(accountPo);
            account.setAccountBalance(AccountBalanceMapper.INSTANCE.convert(accountBalancePo));

            AccountTree accountTree = new AccountTree();
            accountTree.setAccount(account);
            accountTree.setChildren(tree(accountPo.getAccountId()));

            accountTreeList.add(accountTree);
        });

        return accountTreeList;
    }
}
