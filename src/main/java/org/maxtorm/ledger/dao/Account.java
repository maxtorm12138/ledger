package org.maxtorm.ledger.dao;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity(name = "account")
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", columnDefinition = "VARCHAR", nullable = false)
    private String accountId;

    @Column(name = "root_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String rootAccountId;

    @Column(name = "parent_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String parentAccountId = "";


    @Column(name = "name", columnDefinition = "VARCHAR", nullable = false)
    private String name;


    @Column(name = "icon_url", columnDefinition = "VARCHAR", nullable = false)
    private String iconUrl;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    @PostPersist
    void updateRootAccountId() {
        if (rootAccountId == null || rootAccountId.isEmpty()) {
            rootAccountId = accountId;
        }

        if (parentAccountId == null || parentAccountId.isEmpty()) {
            parentAccountId = accountId;
        }

        if (iconUrl == null || iconUrl.isEmpty()) {
            iconUrl = "https://www.google.com.hk/favicon.ico";
        }
    }
}
