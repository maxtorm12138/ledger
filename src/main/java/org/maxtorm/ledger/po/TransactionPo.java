package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.Commodity;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "user_transaction")
@Table(name = "user_transaction", indexes = {@Index(name = "index_transaction_id", columnList = "transaction_id"),})
public class TransactionPo extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_record_id", nullable = false)
    private String transactionRecordId = "";

    @Column(name = "transaction_id", nullable = false)
    private String transactionId = "";

    @Column(name = "source_account_id", nullable = false)
    private String sourceAccountId = "";

    @Column(name = "destination_account_id", nullable = false)
    private String destinationAccountId = "";

    @Column(name = "source_amount", nullable = false)
    private Long source_amount = 0L;

    @Column(name = "destination_amount", nullable = false)
    private Long destination_amount = 0L;

    @Column(name = "source_commodity", nullable = false)
    private String sourceCommodity;

    @Column(name = "destination_commodity", nullable = false)
    private String destinationCommodity;

    public Commodity getSourceCommodity() {
        return Commodity.of(sourceCommodity);
    }

    public void setSourceCommodity(Commodity sourceCommodity) {
        this.sourceCommodity = sourceCommodity.toString();
    }

    public Commodity getDestinationCommodity() {
        return Commodity.of(destinationCommodity);
    }

    public void setDestinationCommodity(Commodity destinationCommodity) {
        this.destinationCommodity = destinationCommodity.toString();
    }
}
