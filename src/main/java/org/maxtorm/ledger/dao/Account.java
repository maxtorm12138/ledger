package org.maxtorm.ledger.dao;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "account")
@Table(name = "account")
public class Account extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", columnDefinition = "VARCHAR", nullable = false)
    private String accountId;

    @Column(name = "root_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String rootAccountId = "";

    @Column(name = "parent_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String parentAccountId = "";

    @Column(name = "depth", columnDefinition = "BIGINT", nullable = false)
    private Integer depth = 0;

    @Column(name = "name", columnDefinition = "VARCHAR", nullable = false)
    private String name = "";

    @Column(name = "icon_url", columnDefinition = "VARCHAR", nullable = false)
    private String iconUrl = "";
}
