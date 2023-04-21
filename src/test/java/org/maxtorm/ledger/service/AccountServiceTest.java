package org.maxtorm.ledger.service;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountBalance;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.util.LedgerDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void open() {
        Account account = new Account();
        account.setName("root");
        account.setIconId(UUID.randomUUID().toString());
        account.setMajorCommodity(Commodity.CurrencyCNY);

        var accountOpened = accountService.open(account);
        assertThat(accountOpened.getAccountId().isEmpty()).isFalse();
    }

    @Test
    void open_duplicate() {
        Account account = new Account();
        account.setName("root");
        account.setIconId(UUID.randomUUID().toString());
        account.setMajorCommodity(Commodity.CurrencyCNY);

        assertThatThrownBy(() -> {
            accountService.open(account);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void path() {
        Account account = new Account();
        account.setName("path_node_a");
        account.setIconId(UUID.randomUUID().toString());
        account.setMajorCommodity(Commodity.CurrencyCNY);

        account = accountService.open(account);

        Account account_1 = new Account();
        account_1.setName("path_node_b");
        account_1.setIconId(UUID.randomUUID().toString());
        account_1.setMajorCommodity(Commodity.CurrencyCNY);
        account_1.setParentAccountId(account.getAccountId());
        account_1 = accountService.open(account_1);

        var path = accountService.path(account_1.getAccountId(), "user_root");

        assertThat(accountService.path(account_1.getAccountId(), "user_root")).hasSize(3);
        assertThat(path.stream().map(Account::getAccountId).toList()).isEqualTo(List.of(account_1.getAccountId(), account.getAccountId(), "user_root"));
    }

    @Test
    void getAccount() {
        {
            Account account = new Account();
            account.setName("account");
            account.setIconId(UUID.randomUUID().toString());
            account.setMajorCommodity(Commodity.CurrencyCNY);
            accountService.open(account);
        }

        var account = accountService.getAccountByName("account");
        assertThat(account.isPresent()).isTrue();
        assertThat(account.get().getAccountBalance()).hasSize(0);
    }

    @Test
    void getAccountWithBalance() {
        {
            Account account = new Account();
            account.setName("accountWithBalance");
            account.setIconId(UUID.randomUUID().toString());
            account.setMajorCommodity(Commodity.CurrencyCNY);
            accountService.open(account);
        }

        var account = accountService.getAccountWithBalanceByName("accountWithBalance");
        assertThat(account.isPresent()).isTrue();
        assertThat(account.get().getAccountBalance()).hasSize(1);
        assertThat(account.get().getAccountBalance()).element(0).extracting(AccountBalance::getBookBalance).isEqualTo(LedgerDecimal.ZERO);
    }
}
