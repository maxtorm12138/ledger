package org.maxtorm.ledger.dao;

import org.maxtorm.ledger.po.AccountPo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountPo, Integer> {

    List<AccountPo> findAccountPoByParentAccountId(String parentAccountId);

    List<AccountPo> findAccountPoByRootAccountId(String rootAccountId);

    Optional<AccountPo> findAccountPoByAccountId(String accountId);

    boolean existsAccountPoByAccountId(String accoundId);

    List<AccountPo> findAccountPoByDepth(Integer depth);
}
