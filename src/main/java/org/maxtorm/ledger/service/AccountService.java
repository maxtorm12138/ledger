package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.dao.AccountSummaryRepository;
import org.maxtorm.ledger.mapper.AccountMapper;
import org.maxtorm.ledger.mapper.AccountSummaryMapper;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.po.AccountSummaryPo;
import org.maxtorm.ledger.po.CommodityPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;


@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private AccountSummaryRepository accountSummaryRepository;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Api.Account create(Api.Account account) {
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
        var accountSummaryBuilder = Api.AccountSummary.newBuilder();
        accountSummaryBuilder.setAccountId(accountToCreate.getAccountId());


        createSummary(accountSummaryBuilder.setCommodity(CommodityPo.of("Currency.CNY").toString()).build());
        createSummary(accountSummaryBuilder.setCommodity(CommodityPo.of("Currency.HKD").toString()).build());

        accountRepository.save(accountToCreate);

        logger.trace("accountCreated: {}", accountCreated);
        return AccountMapper.INSTANCE.Convert(accountCreated);
    }

    @Transactional(value = Transactional.TxType.SUPPORTS)
    public Api.AccountSummary createSummary(Api.AccountSummary accountSummary) {
        var accountSummaryToCreate = AccountSummaryMapper.INSTANCE.Convert(accountSummary);
        var accountSummaryCreated = accountSummaryRepository.save(accountSummaryToCreate);
        logger.trace("accountSummaryCreated: {}", accountSummaryCreated);
        return AccountSummaryMapper.INSTANCE.Convert(accountSummaryCreated);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<Api.AccountTree> tree(String rootAccountId) {
        return buildTree(rootAccountId);
    }

    private List<Api.AccountTree> buildTree(String accountId) {
        var accountTreeList = new ArrayList<Api.AccountTree>();

        List<AccountPo> accountInLevel;
        if (accountId.isEmpty()) {
            accountInLevel = accountRepository.findAccountPoByDepth(0);
        } else {
            accountInLevel = accountRepository.findAccountPoByParentAccountId(accountId);
        }

        accountInLevel.forEach(accountPo -> {
            var accountTreeBuilder = Api.AccountTree.newBuilder();
            accountTreeBuilder.setSelf(AccountMapper.INSTANCE.Convert(accountPo));
            accountTreeBuilder.addAllChild(buildTree(accountPo.getAccountId()));
            accountTreeList.add(accountTreeBuilder.build());
        });

        return accountTreeList;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<Api.Account> pathToRoot(String accountId) {
        ArrayList<Api.Account> path = new ArrayList<>();
        var start = accountRepository.findAccountPoByAccountId(accountId).orElseThrow();
        path.add(AccountMapper.INSTANCE.Convert(start));
        while (!start.getParentAccountId().isEmpty()) {
            start = accountRepository.findAccountPoByAccountId(start.getParentAccountId()).orElseThrow();
            path.add(AccountMapper.INSTANCE.Convert(start));
        }
        return path;
    }
}
