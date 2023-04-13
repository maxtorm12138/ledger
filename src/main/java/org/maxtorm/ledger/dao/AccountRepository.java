package org.maxtorm.ledger.dao;

import org.maxtorm.ledger.obj.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    List<Account> findAccountPoByParentAccountId(String parentAccountId);
    List<Account> findAccountPoByRootAccountId(String rootAccountId);
}
