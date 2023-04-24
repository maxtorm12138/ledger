package org.maxtorm.ledger.broker;

import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.ConvertExchangeFailed;
import org.maxtorm.ledger.broker.exception.GetStockFailed;
import org.maxtorm.ledger.broker.provider.ForeignExchangeProvider;
import org.maxtorm.ledger.broker.provider.StockProvider;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.math.BigDecimal;

public class YahooFinanceProvider implements StockProvider, ForeignExchangeProvider {
    @Override
    public BigDecimal convertExchange(Commodity source, BigDecimal amount, Commodity destination) throws ConvertExchangeFailed {
        try {
            FxQuote fxQuote = YahooFinance.getFx(String.format("%s%s=x", source.getName(), destination.getName()));
            return fxQuote.getPrice().multiply(amount);
        } catch (IOException e) {
            throw new ConvertExchangeFailed(source, destination, "error, {}", e.getMessage());
        }
    }

    @Override
    public StockPrice getStockPrice(Commodity security) throws GetStockFailed {
        try {
            var stock = YahooFinance.get(security.toQualifiedString());
            if (stock == null) {
                throw new GetStockFailed(security, "stock not found");
            }
            return new StockPrice(Commodity.of(String.format("Currency.%s", stock.getCurrency())), stock.getQuote().getPrice());
        } catch (IOException e) {
            throw new GetStockFailed(security, "error, {}", e.getMessage());
        }
    }
}
