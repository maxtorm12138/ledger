package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.commodity.Fund;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IFundProvider {
    Pair<LocalDate, BigDecimal> getFundNav(Fund fund) throws CommodityProviderNetworkFailException;
}
