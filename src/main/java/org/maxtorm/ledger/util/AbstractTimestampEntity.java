package org.maxtorm.ledger.util;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class AbstractTimestampEntity {
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreationTimestamp
    protected ZonedDateTime createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    protected ZonedDateTime updateTime;

//    @PrePersist
//    void prePersist() {
//        createTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
//        updateTime = createTime;
//    }
//
//    @PreUpdate
//    void preUpdate() {
//        updateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
//    }
}
