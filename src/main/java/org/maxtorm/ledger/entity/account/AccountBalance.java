package org.maxtorm.ledger.entity.account;

import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.entity.commodity.Commodity;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountBalance {
    private String accountId;
    private Commodity commodity;
    private BigDecimal bookBalance;
    private BigDecimal totalInflow;
    private BigDecimal totalOutflow;
}
