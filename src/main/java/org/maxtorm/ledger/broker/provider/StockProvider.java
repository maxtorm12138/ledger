package org.maxtorm.ledger.broker.provider;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.GetStockFailed;

import java.math.BigDecimal;

public interface StockProvider {
    record StockPrice(Commodity currency, BigDecimal price) {}

    StockPrice getStockPrice(Commodity stock) throws GetStockFailed;
}
