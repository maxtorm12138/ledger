package org.maxtorm.ledger.entity.commodity.providers;

import org.maxtorm.ledger.entity.commodity.Fund;
import org.maxtorm.ledger.entity.commodity.exception.CommodityProviderNetworkFailException;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IFundProvider {
    Pair<LocalDate, BigDecimal> getFundNav(Fund fund) throws CommodityProviderNetworkFailException;
}
