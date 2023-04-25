package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.commodity.Currency;
import org.maxtorm.ledger.commodity.Stock;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;

public interface IStockPriceProvider {
    Pair<Currency, BigDecimal> getStockPrice(Stock stock) throws CommodityProviderNetworkFailException;
}
