package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.commodity.Fund;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IFundProvider {
    BigDecimal getFundNav(Fund fund) throws CommodityProviderNetworkFailException;

    BigDecimal getFundNav(Fund fund, LocalDate date) throws CommodityProviderNetworkFailException;
}
