package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.AccountExtraInfo;
import org.maxtorm.ledger.bo.Commodity;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "account")
@Table(name = "account", indexes = {@Index(name = "index_root_account_id", columnList = "root_account_id"), @Index(name = "parent_account_id", columnList = "parent_account_id"), @Index(name = "unique_name", columnList = "name", unique = true)})
public class AccountPo extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", nullable = false)
    private String accountId = "";

    @Column(name = "root_account_id", nullable = false)
    private String rootAccountId = "";

    @Column(name = "parent_account_id", nullable = false)
    private String parentAccountId = "";

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "icon_url", nullable = false, length = 1024)
    private String iconUrl = "";

    @Column(name = "major_commodity")
    @Convert(converter = Commodity.CommodityConverter.class)
    private Commodity majorCommodity = Commodity.Undefined;

    @Column(name = "extra_info", nullable = false, length = 2048)
    @Convert(converter = AccountExtraInfo.AccountExtraInfoConverter.class)
    private AccountExtraInfo extraInfo = new AccountExtraInfo();
}
