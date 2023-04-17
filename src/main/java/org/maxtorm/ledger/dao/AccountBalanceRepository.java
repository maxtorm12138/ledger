package org.maxtorm.ledger.dao;

import jakarta.persistence.LockModeType;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.po.CommodityPo;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountBalanceRepository extends CrudRepository<AccountBalancePo, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountBalancePo> getAccountBalancePoByAccountIdAndCommodity(String accountId, CommodityPo commodity);

    Optional<AccountBalancePo> findAccountBalancePoByAccountIdAndCommodity(String accountId, CommodityPo commodity);
}
