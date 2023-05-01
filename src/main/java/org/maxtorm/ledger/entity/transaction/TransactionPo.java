package org.maxtorm.ledger.entity.transaction;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;
import org.maxtorm.ledger.entity.AbstractTimestampEntity;
import org.maxtorm.ledger.entity.commodity.CommodityPo;
import org.maxtorm.ledger.entity.split.SplitPo;

import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "user_transaction")
@Table(name = "user_transaction")
public class TransactionPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "guid", nullable = false)
    private String guid;

    @Column(name = "reference_number", nullable = false)
    private String referenceNUmber;

    @OneToOne
    @JoinColumn(name = "currency_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CommodityPo currency;

    @Column(name = "post_date")
    private ZonedDateTime postDate;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany
    @JoinColumn(name = "tx_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    List<SplitPo> splitList;
}