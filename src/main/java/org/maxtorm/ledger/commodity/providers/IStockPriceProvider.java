package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.commodity.exception.GetStockFailed;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;

public interface IStockPriceProvider {
    Pair<Commodity, BigDecimal> getStockPrice(Commodity stock);
}
