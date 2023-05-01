package org.maxtorm.ledger.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maxtorm.ledger.entity.commodity.CommodityPo;
import org.maxtorm.ledger.entity.AbstractTimestampEntity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
@Entity(name = "account")
@Table(name = "account", indexes = @Index(name = "index_parent_guid", columnList = "parent_guid"))
public class AccountPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "guid", nullable = false)
    private String guid;

    @Column(name = "parent_guid", nullable = false)
    private String parentGuid;

    @Column(name = "placeholder", nullable = false)
    private boolean placeholder;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "icon", nullable = false)
    private String icon;

    @OneToOne
    @JoinColumn(name = "commodity_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private CommodityPo majorCommodity;
}
