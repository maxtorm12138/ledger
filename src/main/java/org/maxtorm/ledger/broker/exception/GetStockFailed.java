package org.maxtorm.ledger.broker.exception;

import lombok.Getter;
import org.maxtorm.ledger.bo.Commodity;
import org.slf4j.helpers.MessageFormatter;

public class GetStockFailed extends Exception {
    public GetStockFailed(Commodity stock, String format, Object... args) {
        super(MessageFormatter.format(format, args).getMessage());
        this.stock = stock;
    }

    @Getter
    private final Commodity stock;
}
