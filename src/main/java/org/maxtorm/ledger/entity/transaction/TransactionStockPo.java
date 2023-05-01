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
//@Entity(name = "user_transaction_stock")
//@Table(name = "user_transaction_stock")
//public class TransactionStockPo extends AbstractTimestampEntity {
//    @Id
//    @Column(name = "reference_number", nullable = false)
//    private String referenceNumber;
//
//    @Column(name = "price", precision = 34, scale = 5, nullable = false)
//    private BigDecimal price;
//
//    @Column(name = "average_cost", precision = 34, scale = 5, nullable = false)
//    private BigDecimal averageCost;
//
//    @Column(name = "dilution_cost", precision = 34, scale = 5, nullable = false)
//    private BigDecimal dilutionCost;
//
//    @Column(name = "totoal_service_charge", precision = 34, scale = 5, nullable = false)
//    private BigDecimal totalServiceCharge;
//
//    @Column(name = "closed", nullable = false)
//    private boolean closed;
//}
