package org.maxtorm.ledger.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AbstractTimestampEntity.AbstractTimestampEntityListener.class)
public abstract class AbstractTimestampEntity {
    public static class AbstractTimestampEntityListener {
        @PrePersist
        public void touchForCreate(Object entity) {
            ((AbstractTimestampEntity)entity).createTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            ((AbstractTimestampEntity)entity).updateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        @PreUpdate
        public void touchForUpdate(Object entity) {
            ((AbstractTimestampEntity)entity).updateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
    }

    @Column(name = "create_time", nullable = false, updatable = false)
    protected String createTime;

    @Column(name = "update_time", nullable = false)
    protected String updateTime;
}
