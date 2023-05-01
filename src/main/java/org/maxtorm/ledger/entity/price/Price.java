package org.maxtorm.ledger.entity.price;

import lombok.*;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.commodity.CommodityPo;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price {
    private String guid;
    private Commodity commodity;
    private Commodity currency;
    private String type;
    private String source;
    private BigInteger value;
    private BigInteger valueDenominator;
    private LocalDate date;
}
