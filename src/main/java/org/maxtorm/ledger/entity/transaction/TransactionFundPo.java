//package org.maxtorm.ledger.entity.transaction;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//import org.maxtorm.ledger.entity.AbstractTimestampEntity;
//
//import java.math.BigDecimal;
//
//@Getter
//@Setter
//@Entity(name = "user_transaction_fund")
//@Table(name = "user_transaction_fund")
//public class TransactionFundPo extends AbstractTimestampEntity {
//    @Id
//    @Column(name = "reference_number", nullable = false)
//    private String referenceNumber;
//
//    @Column(name = "nav", precision = 34, scale = 5, nullable = false)
//    private BigDecimal nav;
//
//    @Column(name = "average_cost", precision = 34, scale = 5, nullable = false)
//    private BigDecimal averageCost;
//
//    @Column(name = "closed", nullable = false)
//    private boolean closed;
//}
