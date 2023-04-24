package org.maxtorm.ledger.broker.provider;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.ConvertExchangeFailed;

import java.math.BigDecimal;

public interface ForeignExchangeProvider {
    BigDecimal convertExchange(Commodity source, BigDecimal amount, Commodity destination) throws ConvertExchangeFailed;
}
