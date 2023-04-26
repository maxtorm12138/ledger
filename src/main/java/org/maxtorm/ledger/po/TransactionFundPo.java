package org.maxtorm.ledger.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "user_transaction_fund")
@Table(name = "user_transaction_fund")
public class TransactionFundPo {
    @Id
    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "nav", precision = 34, scale = 5, nullable = false)
    private BigDecimal nav;

    @Column(name = "average_cost", precision = 34, scale = 5, nullable = false)
    private BigDecimal averageCost;

    @Column(name = "is_closed", nullable = false)
    private boolean isClosed;
}