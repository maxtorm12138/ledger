package org.maxtorm.ledger.entity.price;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.commodity.exception.CommodityProviderNetworkFailException;
import org.maxtorm.ledger.entity.price.providers.EasyMoneyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class EasyMoneyProviderTest {
    private static final Logger logger = LoggerFactory.getLogger(EasyMoneyProviderTest.class);
    @Test
    public void getFundNav() throws CommodityProviderNetworkFailException {
        EasyMoneyProvider provider = new EasyMoneyProvider("http://easymoneyapi:3000");
        Commodity fund = Commodity.builder().mnemonic("004011").namespace("fund::china").fraction(BigInteger.valueOf(10000)).build();
        Commodity currency = Commodity.builder().mnemonic("CNY").namespace("currency").fraction(BigInteger.valueOf(100)).build();

        var price = provider.getQuote(fund, currency);
        logger.info("price: {}", price);
    }
}
