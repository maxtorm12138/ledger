package org.maxtorm.ledger.entity.lock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.maxtorm.ledger.entity.AbstractTimestampEntity;

@Entity(name = "database_lock")
@Table(name = "database_lock")
public class DatabaseLock extends AbstractTimestampEntity {
    @Id
    private String hostname;
}
