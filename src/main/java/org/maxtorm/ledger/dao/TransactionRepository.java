package org.maxtorm.ledger.dao;

import org.maxtorm.ledger.po.TransactionPo;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionPo, String> {
}
