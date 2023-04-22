package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.util.LedgerDecimal;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountBalance {
    private Commodity commodity = Commodity.Undefined;
    private BigDecimal bookBalance = LedgerDecimal.ZERO;
    private BigDecimal totalInflow = LedgerDecimal.ZERO;
    private BigDecimal totalOutflow = LedgerDecimal.ZERO;
}
