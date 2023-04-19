package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountBalance {
    private Commodity commodity = Commodity.Undefined;
    private BigDecimal bookBalance = BigDecimal.valueOf(0);
}
