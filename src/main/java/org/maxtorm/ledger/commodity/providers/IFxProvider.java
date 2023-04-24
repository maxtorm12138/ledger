package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.commodity.Currency;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;

import java.math.BigDecimal;

public interface IFxProvider {
    BigDecimal getFxQuote(Currency from, Currency to) throws CommodityProviderNetworkFailException;

    BigDecimal getFxQuote(Currency from, Currency to, BigDecimal amount) throws CommodityProviderNetworkFailException;
}
