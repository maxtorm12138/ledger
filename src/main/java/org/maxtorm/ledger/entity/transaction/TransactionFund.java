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
public class TransactionFund extends Transaction {
    private BigDecimal nav;
    private BigDecimal averageCost;
    private boolean closed;
}
