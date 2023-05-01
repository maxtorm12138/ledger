package org.maxtorm.ledger.entity.lock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseLockRepository extends JpaRepository<DatabaseLock, String> {
}
