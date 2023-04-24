package org.maxtorm.ledger.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.po.AccountPo;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class EntityInsertRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private String getCreateAndUpdateTime() {
        return ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @Transactional
    public void insertAccount(AccountPo accountPo) {
        var createAndUpdateTime = getCreateAndUpdateTime();
        entityManager.createNativeQuery("""
                        insert into account(
                            account_id,
                            parent_account_id,
                            name,
                            major_commodity,
                            create_time,
                            update_time)
                        values (
                            :account_id,
                            :parent_account_id,
                            :name,
                            :major_commodity,
                            :create_time,
                            :update_time)
                        """)
                .setParameter("account_id", accountPo.getAccountId())
                .setParameter("parent_account_id", accountPo.getParentAccountId())
                .setParameter("name", accountPo.getName())
                .setParameter("major_commodity", accountPo.getMajorCommodity().toString())
                .setParameter("create_time", createAndUpdateTime)
                .setParameter("update_time", createAndUpdateTime)
                .executeUpdate();

    }


    @Transactional
    public void insertAccountBalance(AccountBalancePo accountBalancePo) {
        var createAndUpdateTime = getCreateAndUpdateTime();
        entityManager.createNativeQuery("""
                        insert into account_balance(
                            account_id,
                            commodity,
                            book_balance,
                            total_inflow,
                            total_outflow,
                            create_time,
                            update_time)
                        values (
                            :account_id,
                            :commodity,
                            :book_balance,
                            :total_inflow,
                            :total_outflow,
                            :create_time,
                            :update_time)""")
                .setParameter("account_id", accountBalancePo.getAccountId())
                .setParameter("commodity", accountBalancePo.getCommodity().toString())
                .setParameter("book_balance", accountBalancePo.getBookBalance())
                .setParameter("total_inflow", accountBalancePo.getTotalInflow())
                .setParameter("total_outflow", accountBalancePo.getTotalOutflow())
                .setParameter("create_time", createAndUpdateTime)
                .setParameter("update_time", createAndUpdateTime)
                .executeUpdate();
    }
}
