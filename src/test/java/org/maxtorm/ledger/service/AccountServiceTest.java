package org.maxtorm.ledger.service;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void open() {
        Account account = new Account();
        account.setName("root");
        account.setMajorCommodity(Commodity.CurrencyCNY);

        var accountOpened = accountService.open(account);
        assertThat(accountOpened.getAccountId().isEmpty()).isFalse();
    }

    @Test
    public void Transfer() {
        // open two account
        Account account1 = new Account();
        account1.setName("account1");

        Account account2 = new Account();
        account2.setName("account2");

        account1 = accountService.open(account1);
        account2 = accountService.open(account2);

        accountService.transfer(account1.getAccountId(), Commodity.CurrencyCNY, BigDecimal.valueOf(10));
        accountService.transfer(account2.getAccountId(), Commodity.CurrencyCNY, BigDecimal.valueOf(-10));
    }
}
