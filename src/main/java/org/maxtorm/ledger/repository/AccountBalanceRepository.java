package org.maxtorm.ledger.repository;

import jakarta.persistence.LockModeType;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountBalanceRepository extends CrudRepository<AccountBalancePo, String> {

    List<AccountBalancePo> findAccountBalancePosByAccountId(String accountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from account_balance a where a.accountId = ?1")
    List<AccountBalancePo> getAccountBalancePos(String accountId);

    @Query("select a from account_balance a where a.accountId = ?1")
    List<AccountBalancePo> findAccountBalancePos(String accountId);
}
