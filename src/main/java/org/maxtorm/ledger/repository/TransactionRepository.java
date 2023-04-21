package org.maxtorm.ledger.repository;

import org.maxtorm.ledger.po.TransactionPo;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionPo, String> {
}
