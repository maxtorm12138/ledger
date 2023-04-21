package org.maxtorm.ledger.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.maxtorm.ledger.bo.Commodity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class AccountBalanceInsertRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addBalance(String accountId, Commodity commodity, BigDecimal amountToAdd) {
        var createTimeAndUpdateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        entityManager.createNativeQuery("insert into account_balance(account_id, commodity, create_time, update_time, book_balance) VALUES (?1,?2,?3,?3,?4) on duplicate key update book_balance = book_balance + ?4, update_time = ?3")
                .setParameter(1, accountId)
                .setParameter(2, commodity.toString())
                .setParameter(3, createTimeAndUpdateTime)
                .setParameter(4, amountToAdd)
                .executeUpdate();
    }
}
