package org.maxtorm.ledger.obj;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTimestampEntity {
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

  @Column(name = "create_time", columnDefinition = "TEXT", nullable = false, updatable = false)
  protected String createTime;


  @Column(name = "update_time", columnDefinition = "TEXT", nullable = false)
  protected String updateTime;

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
