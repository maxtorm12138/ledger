package org.maxtorm.ledger.entity.transaction;

import org.maxtorm.ledger.entity.transaction.TransactionPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends JpaRepository<TransactionPo, String> {
}
