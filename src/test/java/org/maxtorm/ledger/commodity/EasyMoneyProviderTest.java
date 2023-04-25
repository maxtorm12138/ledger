package org.maxtorm.ledger.commodity;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;
import org.maxtorm.ledger.commodity.providers.EasyMoneyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyMoneyProviderTest {
    private static final Logger logger = LoggerFactory.getLogger(EasyMoneyProviderTest.class);
    @Test
    public void getFundNav() throws CommodityProviderNetworkFailException {
        EasyMoneyProvider provider = new EasyMoneyProvider();
        var nav = provider.getFundNav(new Fund("fund.004011"));
        logger.info("nav: {}", nav);
    }
}
