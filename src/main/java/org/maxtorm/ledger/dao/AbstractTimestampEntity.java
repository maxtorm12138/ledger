package org.maxtorm.ledger.dao;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTimestampEntity {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Column(name = "create_time", columnDefinition = "VARCHAR", nullable = false, updatable = false)
  protected Date createTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Column(name = "update_time", columnDefinition = "VARCHAR", nullable = false)
  protected Date updateTime;

  @PrePersist
  protected void onCreate() {
    createTime = new Date();
    updateTime = createTime;
  }

  @PreUpdate
  protected void onUpdate() {
    updateTime = new Date();
  }
}
