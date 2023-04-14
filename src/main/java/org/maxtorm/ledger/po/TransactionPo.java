package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "user_transaction")
@Table(name = "user_transaction", indexes = {@Index(name = "index_transaction_id", columnList = "transaction_id"),})
public class TransactionPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "transaction_record_id", columnDefinition = "VARCHAR", nullable = false)
    private String transactionRecordId = "";

    @Column(name = "transaction_id", columnDefinition = "VARCHAR", nullable = false)
    private String transactionId = "";

    @Column(name = "source_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String sourceAccountId = "";

    @Column(name = "destination_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String destinationAccountId = "";

    @Column(name = "amount", columnDefinition = "BIGINT", nullable = false)
    private Long amount = 0L;

}
