package org.maxtorm.ledger.dao;

import jakarta.persistence.LockModeType;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountBalanceRepository extends CrudRepository<AccountBalancePo, String> {

    List<AccountBalancePo> findAccountBalancePosByAccountId(String accountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<AccountBalancePo> getAccountBalancePosByAccountId(String accountId);
}
