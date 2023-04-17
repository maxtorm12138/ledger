package org.maxtorm.ledger.service;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.po.CommodityPo;
import org.maxtorm.ledger.proto.Api;
import org.maxtorm.ledger.proto.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void addAccountBalance() {
        var accountBuilder = Entity.Account.newBuilder();
        accountBuilder.setName("root");
        var account = accountService.open(accountBuilder.build());

        var accountBalanceBuilder = Entity.AccountBalance.newBuilder();
        accountBalanceBuilder.setAmount(0L);
        accountBalanceBuilder.setCommodity(CommodityPo.of("Currency.CNY").toString());
        accountBalanceBuilder.setAccountId(account.getAccountId());

        var accountBalance = accountService.addAccountBalance(accountBalanceBuilder.build(), 1000L);
    }
}
