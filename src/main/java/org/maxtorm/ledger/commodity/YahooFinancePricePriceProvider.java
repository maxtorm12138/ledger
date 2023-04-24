package org.maxtorm.ledger.commodity;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.commodity.providers.IFxPriceProvider;
import org.maxtorm.ledger.commodity.providers.IStockPriceProvider;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.util.Pair;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class YahooFinancePricePriceProvider implements IStockPriceProvider, IFxPriceProvider {

    @Override
    public BigDecimal getCurrencyPrice(Commodity sourceCurrency, Commodity destinationCurrency) {
        try {
            var fx = YahooFinance.getFx(MessageFormatter.format("{}{}=x", sourceCurrency.getName(), destinationCurrency.getName()).getMessage());
            return fx.getPrice();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pair<Commodity, BigDecimal> getStockPrice(Commodity stock) {
        return null;
    }
}
