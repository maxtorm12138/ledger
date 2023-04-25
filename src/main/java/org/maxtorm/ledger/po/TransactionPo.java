package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.TransactionState;
import org.maxtorm.ledger.commodity.Commodity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "user_transaction")
@Table(
    name = "user_transaction",
    indexes = {
        @Index(name = "index_reference_number", columnList = "reference_number"),
        @Index(name = "index_initiator_account_id", columnList = "initiator_account_id"),
        @Index(name = "index_receiver_account_id", columnList = "receiver_account_id")})
public class TransactionPo extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id", nullable = false)
    private String transaction_id;

    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "category", nullable = false)
    private String category;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sequence_number", nullable = false)
    private Long sequenceNumber;

    @Column(name = "initiate_date")
    private LocalDate initiateDate;

    @Column(name = "transaction_state", nullable = false)
    private TransactionState transactionState;

    @Column(name = "note", nullable = false, length = 4096)
    private String note;

    // account pair
    //////////////////////////////////////////////////////////////////////////////////
    @Column(name = "initiator_account_id", nullable = false)
    private String initiatorAccountId;

    @Column(name = "receiver_account_id", nullable = false)
    private String receiverAccountId;
    //////////////////////////////////////////////////////////////////////////////////

    // commodity pair
    //////////////////////////////////////////////////////////////////////////////////
    @Column(name = "initiator_commodity", nullable = false)
    private Commodity initiatorCommodity;

    @Column(name = "receiver_commodity", nullable = false)
    private Commodity receiverCommodity;
    //////////////////////////////////////////////////////////////////////////////////

    // amount pair
    //////////////////////////////////////////////////////////////////////////////////
    @Column(name = "initiator_amount", nullable = false, precision = 34, scale = 5)
    private BigDecimal initiatorAmount;

    @Column(name = "receiver_amount", nullable = false, precision = 34, scale = 5)
    private BigDecimal receiverAmount;
    //////////////////////////////////////////////////////////////////////////////////

    // book balance snapshot
    //////////////////////////////////////////////////////////////////////////////////
    @Column(name = "initiator_book_balance_snapshot", nullable = false, precision = 34, scale = 5)
    private BigDecimal initiatorBookBalanceSnapshot;

    @Column(name = "initiator_book_balance_snapshot", nullable = false, precision = 34, scale = 5)
    private BigDecimal receiverBookBalanceSnapshot;
    //////////////////////////////////////////////////////////////////////////////////
    

    // extend extra info for stock if not stock then it's null
    @OneToOne
    @JoinColumn(name = "reference_number", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private TransactionStockPo stock;
}
