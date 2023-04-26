package org.maxtorm.ledger.entity.commodity.providers;

import org.maxtorm.ledger.entity.commodity.Currency;
import org.maxtorm.ledger.entity.commodity.Stock;
import org.maxtorm.ledger.entity.commodity.exception.CommodityProviderNetworkFailException;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;

public interface IStockPriceProvider {
    Pair<Currency, BigDecimal> getStockPrice(Stock stock) throws CommodityProviderNetworkFailException;
}
