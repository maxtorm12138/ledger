package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "account")
@Table(name = "account", indexes = {@Index(name = "index_root_account_id", columnList = "root_account_id"), @Index(name = "parent_account_id", columnList = "parent_account_id"), @Index(name = "unique_name", columnList = "name", unique = true)})
public class AccountPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_id", nullable = false)
    private String accountId = "";

    @Column(name = "root_account_id", nullable = false)
    private String rootAccountId = "";

    @Column(name = "parent_account_id", nullable = false)
    private String parentAccountId = "";

    @Column(name = "depth", nullable = false)
    private Integer depth = 0;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "icon_url", nullable = false, length = 1024)
    private String iconUrl = "";

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<AccountSummaryPo> accountSummaries = new ArrayList<>();
}
