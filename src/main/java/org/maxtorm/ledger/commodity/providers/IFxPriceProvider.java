package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.commodity.exception.ConvertExchangeFailed;

import java.math.BigDecimal;

public interface IFxPriceProvider {
    BigDecimal getCurrencyPrice(Commodity sourceCurrency, Commodity destinationCurrency);
}
