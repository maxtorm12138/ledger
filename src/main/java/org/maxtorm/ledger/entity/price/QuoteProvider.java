package org.maxtorm.ledger.entity.price;

import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.price.Price;

public interface QuoteProvider {
    boolean isSupport(Commodity commodity, Commodity currency);
    String getName();
    Price getQuote(Commodity commodity, Commodity currency);
}
