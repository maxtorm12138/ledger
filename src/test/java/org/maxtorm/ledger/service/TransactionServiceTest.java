package org.maxtorm.ledger.service;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.bo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Test
    public void transfer() {
        Account account = new Account();
        account.setName("account");
        account.setIconId(UUID.randomUUID().toString());
        account.setMajorCommodity(Commodity.CurrencyCNY);

        account = accountService.open(account);

        Account account1 = new Account();
        account1.setName("account1");
        account1.setIconId(UUID.randomUUID().toString());
        account1.setMajorCommodity(Commodity.CurrencyCNY);

        account1 = accountService.open(account1);


        Transaction transaction = new Transaction();

        transaction.setSourceAccountId(account.getAccountId());
        transaction.setDestinationAccountId(account1.getAccountId());

        transaction.setSourceCommodity(Commodity.CurrencyCNY);
        transaction.setDestinationCommodity(Commodity.CurrencyCNY);

        transaction.setSourceCommodityAmount(BigDecimal.valueOf(1000));
        transaction.setDestinationCommodityAmount(BigDecimal.valueOf(1000));

        transaction.setExtraInfoTag(Transaction.ExtraInfoTag.Transfer);

        transactionService.transfer(transaction);
    }
}
