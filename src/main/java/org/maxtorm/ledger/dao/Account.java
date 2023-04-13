package org.maxtorm.ledger.dao;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", columnDefinition = "TEXT")
    private String accountId;

    @Column(name = "root_account_id", columnDefinition = "TEXT")
    private String rootAccountId;

    @Column(name = "parent_account_id", columnDefinition = "TEXT")
    private String parentAccountId;


    @Column(name = "name", columnDefinition = "TEXT")
    private String name;


    @Column(name = "icon_url", columnDefinition = "TEXT")
    private String iconUrl;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;
}
