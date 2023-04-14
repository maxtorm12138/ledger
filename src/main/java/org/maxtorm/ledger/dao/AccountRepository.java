package org.maxtorm.ledger.dao;

import jakarta.persistence.LockModeType;
import org.maxtorm.ledger.po.AccountPo;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountPo, Integer> {

    List<AccountPo> findAccountPoByParentAccountId(String parentAccountId);
    List<AccountPo> findAccountPoByRootAccountId(String rootAccountId);
    Optional<AccountPo> findAccountPoByAccountId(String accountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountPo> getAccountPoByAccountId(String accountId);
}
