package org.maxtorm.ledger.broker;

import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.ConvertExchangeFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class CoinMarketCapProviderTest {
    private static final Logger logger = LoggerFactory.getLogger(CoinMarketCapProvider.class);
    @Test
    public void convertCnyToUsd () {
        String apiKey = System.getenv().get("COIN_MARKET_CAP_API_KEY");
        CoinMarketCapProvider provider = new CoinMarketCapProvider(apiKey);

        try {
            var amountCny = BigDecimal.valueOf(100);
            var amountUsd = provider.convertExchange(Commodity.CurrencyCNY, amountCny, Commodity.CurrencyUSD);
            logger.info("convert {} {} to {} {}", amountCny, Commodity.CurrencyCNY.getName(), amountUsd, Commodity.CurrencyUSD.getName());
        } catch (ConvertExchangeFailed e) {
            throw new RuntimeException(e);
        }
    }
}
