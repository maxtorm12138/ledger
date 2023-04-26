package org.maxtorm.ledger.entity.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStock extends Transaction {
    private BigDecimal price;
    private BigDecimal averageCost;
    private BigDecimal dilutionCost;
    private BigDecimal totalServiceCharge;
    private boolean closed;
}
