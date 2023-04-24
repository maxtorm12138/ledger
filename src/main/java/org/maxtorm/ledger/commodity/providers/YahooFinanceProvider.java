package org.maxtorm.ledger.commodity.providers;

import org.maxtorm.ledger.commodity.Currency;
import org.maxtorm.ledger.commodity.exception.CommodityProviderNetworkFailException;
import org.slf4j.helpers.MessageFormatter;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class YahooFinanceProvider implements IFxProvider {

    @Override
    public BigDecimal getFxQuote(Currency from, Currency to) throws CommodityProviderNetworkFailException {
        return getFxQuote(from, to, BigDecimal.ONE);
    }

    @Override
    public BigDecimal getFxQuote(Currency from, Currency to, BigDecimal amount) throws CommodityProviderNetworkFailException {
        try {
            var fx = YahooFinance.getFx(String.format("%s%s=x", from.getDisplayName(), to.getDisplayName()));
            if (fx == null) {
                throw new NoSuchElementException(MessageFormatter.format("no such fx {}{}", from, to).getMessage());
            }

            return fx.getPrice().multiply(amount);
        } catch (IOException e) {
            throw new CommodityProviderNetworkFailException(from, to, "getFx fail error: {}", e.getMessage());
        }
    }
}
