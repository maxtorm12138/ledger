package org.maxtorm.ledger.entity.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStock extends Transaction {
    private BigDecimal price;
    private BigDecimal averageCost;
    private BigDecimal dilutionCost;
    private BigDecimal totalServiceCharge;
    private boolean closed;
}
