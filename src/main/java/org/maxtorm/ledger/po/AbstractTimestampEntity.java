package org.maxtorm.ledger.po;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class AbstractTimestampEntity {
    @Column(name = "create_time", columnDefinition = "TEXT", nullable = false, updatable = false)
    protected String createTime = "1970-01-01T00:00:00.0Z";
    @Column(name = "update_time", columnDefinition = "TEXT", nullable = false)
    protected String updateTime = "1970-01-01T00:00:00.0Z";

    public ZonedDateTime getCreateTime() {
        return ZonedDateTime.parse(createTime);
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public ZonedDateTime getUpdateTime() {
        return ZonedDateTime.parse(updateTime);
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @PrePersist
    void prePersist() {
        createTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        updateTime = createTime;
    }

    @PreUpdate
    void preUpdate() {
        updateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
