package org.maxtorm.ledger.entity.account;

import jakarta.persistence.LockModeType;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface AccountBalanceRepository extends JpaRepository<AccountBalancePo, String> {

    List<AccountBalancePo> findAccountBalancePosByAccountId(String accountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<AccountBalancePo> getAccountBalancePosByAccountId(String accountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountBalancePo> getAccountBalancePoByAccountIdAndCommodity(String accountId, Commodity commodity);
}
