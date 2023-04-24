package org.maxtorm.ledger.broker;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.ConvertExchangeFailed;
import org.maxtorm.ledger.broker.exception.GetStockFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class YahooFinanceProviderTest {
    private static final Logger logger = LoggerFactory.getLogger(YahooFinanceProviderTest.class);
    @Test
    public void convertCnyToUsd() {
        YahooFinanceProvider provider = new YahooFinanceProvider();
        try {
            var amountCny = BigDecimal.valueOf(100);
            BigDecimal amountUsd;
            amountUsd = provider.convertExchange(Commodity.CurrencyCNY, amountCny, Commodity.CurrencyUSD);
            logger.info("convert {} {} to {} {}", amountCny, Commodity.CurrencyCNY.getName(), amountUsd, Commodity.CurrencyUSD.getName());
        } catch (ConvertExchangeFailed e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getStockPrice() {
        YahooFinanceProvider provider = new YahooFinanceProvider();

        try {
            var price = provider.getStockPrice(Commodity.of("Security.0700.HK"));
            logger.info("00700.HK price: {}", price);
        } catch (GetStockFailed e) {
            throw new RuntimeException(e);
        }
    }
}
