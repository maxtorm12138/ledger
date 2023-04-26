package org.maxtorm.ledger.entity.commodity.providers;

import org.maxtorm.ledger.entity.commodity.Currency;
import org.maxtorm.ledger.entity.commodity.exception.CommodityProviderNetworkFailException;

import java.math.BigDecimal;

public interface IFxProvider {
    BigDecimal getFxQuote(Currency from, Currency to) throws CommodityProviderNetworkFailException;

    BigDecimal getFxQuote(Currency from, Currency to, BigDecimal amount) throws CommodityProviderNetworkFailException;
}
