package org.maxtorm.ledger.broker.provider;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.ConvertExchangeFailed;

import java.math.BigDecimal;

public interface IForeignExchangeProvider {
    BigDecimal convertCurrency(Commodity sourceCurrency, BigDecimal amount, Commodity destinationCurrency) throws ConvertExchangeFailed;
    BigDecimal getCurrencyPrice(Commodity sourceCurrency, Commodity destinationCurrency);
}
