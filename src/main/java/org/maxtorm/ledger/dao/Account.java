package org.maxtorm.ledger.dao;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", columnDefinition = "TEXT")
    private String accountId;

    @Getter
    @Setter
    @Column(name = "root_account_id", columnDefinition = "TEXT")
    private String rootAccountId;

    @Getter
    @Setter
    @Column(name = "parent_account_id", columnDefinition = "TEXT")
    private String parentAccountId;


    @Getter
    @Setter
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;


    @Getter
    @Setter
    @Column(name = "icon_url", columnDefinition = "TEXT")
    private String iconUrl;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

}
