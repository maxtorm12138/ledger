package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.Commodity;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "user_transaction")
@Table(name = "user_transaction", indexes = {@Index(name = "index_reference_number", columnList = "reference_number"),})
public class TransactionPo extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reference_number", nullable = false)
    private String referenceNumber = "";

    @Column(name = "source_account_id", nullable = false)
    private String sourceAccountId = "";

    @Column(name = "source_commodity", nullable = false)
    @Convert(converter = Commodity.CommodityConverter.class)
    private Commodity sourceCommodity = Commodity.Undefined;

    @Column(name = "source_commodity_amount", nullable = false)
    private BigDecimal sourceCommodityAmount = BigDecimal.ZERO;

    @Column(name = "destination_account_id", nullable = false)
    private String destinationAccountId = "";

    @Column(name = "destination_commodity", nullable = false)
    @Convert(converter = Commodity.CommodityConverter.class)
    private Commodity destinationCommodity = Commodity.Undefined;

    @Column(name = "destination_commodity_amount", nullable = false)
    private BigDecimal destinationCommodityAmount = BigDecimal.ZERO;
}
