package org.maxtorm.ledger.entity.price;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maxtorm.ledger.entity.commodity.CommodityPo;
import org.maxtorm.ledger.entity.AbstractTimestampEntity;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "price")
@Table(name = "price")
public class PricePo extends AbstractTimestampEntity {
    @Id
    private String guid;

    @OneToOne
    @JoinColumn(name = "commodity_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private CommodityPo commodity;

    @OneToOne
    @JoinColumn(name = "currency_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private CommodityPo currency;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "value", nullable = false)
    private BigInteger value;

    @Column(name = "value_denominator", nullable = false)
    private BigInteger valueDenominator;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
