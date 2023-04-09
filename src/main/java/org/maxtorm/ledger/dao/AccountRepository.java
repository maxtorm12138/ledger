package org.maxtorm.ledger.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
