package org.maxtorm.ledger.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.maxtorm.ledger.po.AccountBalancePo;
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
    public void addBalance(AccountBalancePo accountBalancePo, BigDecimal amountToAdd) {

        var createTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        entityManager.createNativeQuery("insert into account_balance set account_id=?1,commodity=?2,amount=?3,create_time=?4,update_time=?5 on duplicate key update update_time=?5,amount=amount+?3")
                .setParameter(1, accountBalancePo.getAccountId())
                .setParameter(2, accountBalancePo.getCommodity().toString())
                .setParameter(3, amountToAdd)
                .setParameter(4, createTime)
                .setParameter(5, createTime)
                .executeUpdate();
    }
}
