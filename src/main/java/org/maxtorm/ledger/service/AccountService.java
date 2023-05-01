package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.entity.account.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
//    private LedgerConfig ledgerConfig;

//    @EventListener(ContextRefreshedEvent.class)
//    @Transactional(Transactional.TxType.REQUIRED)
//    public void initialize() {
//        open(
//                Account.builder()
//                        .accountId("system_root")
//                        .name("system_root")
//                        .icon("system")
//                        .parentAccountId("")
//                        .description("system root")
//                        .majorCommodity(ledgerConfig.getAccountInitializeProperties().getMajorCommodity())
//                        .build());
//
//        initializeImpl(ledgerConfig.getAccountInitializeProperties().getAccountsToInitialize(), "system_root");
//    }
//
//    private void initializeImpl(HashMap<String, AccountInitializeProperties.AccountInitialize> accountToInitializeMap, String parentAccountId) {
//        if (accountToInitializeMap == null) {
//            return;
//        }
//
//        accountToInitializeMap.forEach((id, accountToInitialize) -> {
//            Account account = new Account();
//            BeanUtils.copyProperties(accountToInitialize, account);
//
//            account.setAccountId(id);
//            if (parentAccountId == null || parentAccountId.isEmpty()) {
//                account.setParentAccountId("system_root");
//            } else {
//                account.setParentAccountId(parentAccountId);
//            }
//
//            if (account.getMajorCommodity() == null) {
//                account.setMajorCommodity(ledgerConfig.getAccountInitializeProperties().getMajorCommodity());
//            }
//
//            if (account.getDescription() == null) {
//                account.setDescription(account.getName());
//            }
//
//            if (account.getIcon() == null) {
//                account.setIcon("none");
//            }
//
//            open(account);
//
//            initializeImpl(accountToInitialize.getChildren(), id);
//        });
//    }
//
//    @Transactional(value = Transactional.TxType.REQUIRED)
//    public List<AccountTree> tree(String parentAccountId) {
//        Function<String, List<Account>> findAccountWithBalanceByParentAccountId = (pAccountId) -> {
//            var accountPoList = accountRepository.findAccountPosByParentAccountId(parentAccountId);
//            if (accountPoList.isEmpty()) {
//                return List.of();
//            }
//
//            var accountList = AccountMapper.INSTANCE.convertPosToBos(accountPoList);
//
//            for (Account account : accountList) {
//                var accountBalancePoList = accountBalanceRepository.findAccountBalancePosByAccountId(account.getAccountId());
//                account.setAccountBalance(AccountBalanceMapper.INSTANCE.convertPosToBos(accountBalancePoList));
//            }
//
//            return accountList;
//        };
//
//
//        List<AccountTree> accountTreeList = new ArrayList<>();
//        List<Account> accountList = findAccountWithBalanceByParentAccountId.apply(parentAccountId);
//        accountList.forEach(account -> {
//            AccountTree accountTree = AccountTreeMapper.INSTANCE.convert(account);
//            accountTree.setChildren(tree(account.getAccountId()));
//            accountTreeList.add(accountTree);
//        });
//
//        return accountTreeList;
//    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void create(Account account) {
        AccountPo accountPo = accountMapper.convertBoToPo(account);
        accountRepository.saveAndFlush(accountPo);
    }
}
