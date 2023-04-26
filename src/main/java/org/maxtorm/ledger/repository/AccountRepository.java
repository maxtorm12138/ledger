package org.maxtorm.ledger.repository;

import jakarta.persistence.LockModeType;
import org.maxtorm.ledger.entity.account.AccountPo;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountPo, Integer> {
    Optional<AccountPo> findAccountPoByAccountId(String accountId);

    List<AccountPo> findAccountPosByParentAccountId(String parentAccountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountPo> getAccountPoByAccountId(String accountId);

    boolean existsAccountPoByName(String name);

    Optional<AccountPo> findAccountPoByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountPo> getAccountPoByName(String name);
}
