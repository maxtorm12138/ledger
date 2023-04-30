package org.maxtorm.ledger.entity.transaction;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionPo, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<TransactionPo> getTransactionPosByReferenceNumber(String referenceNumber);
}
