package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.dao.AccountSummaryRepository;
import org.maxtorm.ledger.mapper.AccountMapper;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.po.AccountSummaryPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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


        // initialize some commodity summary
        var summaries = new ArrayList<AccountSummaryPo>();

        summaries.add(new AccountSummaryPo("", accountToCreate.getAccountId(), "CNY", 0L));
        summaries.add(new AccountSummaryPo("", accountToCreate.getAccountId(), "HKD", 0L));
        summaries.add(new AccountSummaryPo("", accountToCreate.getAccountId(), "USD", 0L));

        var savedSummaries = accountSummaryRepository.saveAll(summaries);
        logger.trace("savedSummaries: {}", savedSummaries);
        summaries.clear();
        savedSummaries.forEach(summaries::add);

        accountToCreate.setAccountSummaries(new ArrayList<>());

        var accountCreated = accountRepository.save(accountToCreate);
        accountCreated.setAccountSummaries(summaries);

        logger.trace("accountCreated: {}", accountCreated);
        return AccountMapper.INSTANCE.Convert(accountCreated);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<Api.AccountTree> tree(String rootAccountId) {
        return buildAccountTree(rootAccountId);
    }

    private List<Api.AccountTree> buildAccountTree(String rootAccountId) {
        var accountTreeList = new ArrayList<Api.AccountTree>();

        List<AccountPo> accountInLevel;
        if (rootAccountId.isEmpty()) {
            accountInLevel = accountRepository.findAccountPoByDepth(0);
        } else {
            accountInLevel = accountRepository.findAccountPoByParentAccountId(rootAccountId);
        }

        accountInLevel.forEach(accountPo -> {
            var accountTreeBuilder = Api.AccountTree.newBuilder();
            accountTreeBuilder.setSelf(AccountMapper.INSTANCE.Convert(accountPo));
            accountTreeBuilder.addAllChild(buildAccountTree(accountPo.getAccountId()));
            accountTreeList.add(accountTreeBuilder.build());
        });

        return accountTreeList;

    }
}
