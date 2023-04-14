package org.maxtorm.ledger.po;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "account")
@Table(name = "account", indexes = {@Index(name = "index_root_account_id", columnList= "root_account_id"), @Index(name = "parent_account_id", columnList = "parent_account_id")})
public class AccountPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_id", columnDefinition = "VARCHAR", nullable = false)
    private String accountId = "";

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

    @Column(name = "balance", columnDefinition = "INTEGER", nullable = false)
    private Long balance = 0L;

    @Column(name = "total_inflow", columnDefinition = "INTEGER", nullable = false)
    private Long totalInflow = 0L;

    @Column(name = "total_outflow", columnDefinition = "INTEGER", nullable = false)
    private Long totalOutflow = 0L;
}
