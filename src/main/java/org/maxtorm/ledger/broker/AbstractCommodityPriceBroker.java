package org.maxtorm.ledger.broker;

import java.util.List;

public abstract class AbstractCommodityPriceBroker {
    public abstract List<SupportCommodity> getSupportCommodities();

    public enum SupportCommodity {
        Fund,
        Stock,
        Etf,
        Future,
        ForeignExchange,
        Option
    }
}
