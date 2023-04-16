package org.maxtorm.ledger.broker;

import java.util.List;

public abstract class AbstractCommodityPriceBroker {
    public enum SupportCommodity {
        Fund,
        Stock,
        Etf,
        Future,
        ForeignExchange,
        Option
    }

    public abstract List<SupportCommodity> getSupportCommodities();
}
