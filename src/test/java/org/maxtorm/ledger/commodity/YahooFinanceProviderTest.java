package org.maxtorm.ledger.commodity;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;
import org.maxtorm.ledger.commodity.providers.IFxProvider;
import org.maxtorm.ledger.commodity.providers.YahooFinanceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YahooFinanceProviderTest {
    private static final Logger logger = LoggerFactory.getLogger(YahooFinanceProviderTest.class);
    @Test
    public void cnyToUsd() throws CommodityProviderNetworkFailException {
        IFxProvider provider = new YahooFinanceProvider();
        var amount = provider.getFxQuote(Currency.CNY, Currency.USD);
        logger.info("CNYUSD: {}", amount);
    }
}
