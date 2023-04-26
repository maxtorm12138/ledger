package org.maxtorm.ledger.entity.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionFund extends Transaction {
    private BigDecimal nav;
    private BigDecimal averageCost;
    private boolean closed;
}
