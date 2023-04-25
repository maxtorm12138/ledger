package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.commodity.Commodity;
import org.maxtorm.ledger.commodity.CommodityAttributeConverter;


@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "account")
@Table(name = "account", indexes = {@Index(name = "parent_account_id", columnList = "parent_account_id"), @Index(name = "unique_name", columnList = "name", unique = true)})
public class AccountPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "parent_account_id", nullable = false)
    private String parentAccountId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "major_commodity", nullable = false)
    @Convert(converter = CommodityAttributeConverter.class)
    private Commodity majorCommodity;
}
