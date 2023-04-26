package org.maxtorm.ledger.entity.transaction;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFund extends Transaction {
    private BigDecimal nav;
    private BigDecimal averageCost;
    private boolean closed;
}
