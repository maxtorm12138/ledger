package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.Transaction;
import org.maxtorm.ledger.bo.TransactionExtraInfo;
import org.maxtorm.ledger.commodity.Commodity;
import org.maxtorm.ledger.commodity.CommodityAttributeConverter;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "user_transaction")
@Table(name = "user_transaction", indexes = {@Index(name = "index_reference_number", columnList = "reference_number"),})
public class TransactionPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "transaction_id", nullable = false)
    private String transaction_id;

    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "source_account_id", nullable = false)
    private String sourceAccountId;

    @Column(name = "source_commodity", nullable = false)
    @Convert(converter = CommodityAttributeConverter.class)
    private Commodity sourceCommodity;

    @Column(name = "source_commodity_amount", nullable = false, precision = 34, scale = 5)
    private BigDecimal sourceCommodityAmount;

    @Column(name = "destination_account_id", nullable = false)
    private String destinationAccountId;

    @Column(name = "destination_commodity", nullable = false)
    @Convert(converter = CommodityAttributeConverter.class)
    private Commodity destinationCommodity;

    @Column(name = "destination_commodity_amount", nullable = false, precision = 34, scale = 5)
    private BigDecimal destinationCommodityAmount;

    @Column(name = "transaction_state", nullable = false)
    private Transaction.TransactionState transactionState;

    @Column(name = "extra_info", nullable = false, length = 2048)
    @Convert(converter = TransactionExtraInfo.TransactionExtraInfoConverter.class)
    private TransactionExtraInfo transactionExtraInfo;

}
