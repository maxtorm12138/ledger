package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.commodity.Commodity;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;

public interface IStockPriceProvider {
    Pair<Commodity, BigDecimal> getStockPrice(Commodity stock) throws CommodityProviderNetworkFailException;
}
