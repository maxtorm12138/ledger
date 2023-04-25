package org.maxtorm.ledger.po;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user_transaction_stock")
@Table(name = "user_transaction_stock")
public class TransactionStockPo extends AbstractTimestampEntity {
  @Id
  @Column(name = "transaction_stock_id", nullable = false)
  private String transactionStockId;

  @Column(name = "reference_number", nullable = false)
  private String referenceNumber;

  @Column(name = "price", precision = 34, scale = 5, nullable = false)
  private BigDecimal price;

  @Column(name = "service_charge", precision = 34, scale = 5, nullable = false)
  private BigDecimal serviceCharge;
}
