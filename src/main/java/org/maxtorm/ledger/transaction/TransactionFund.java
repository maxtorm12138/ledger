package org.maxtorm.ledger.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionFund extends Transaction {
    private BigDecimal nav;
    private BigDecimal averageCost;
    private boolean isClosed;
}
