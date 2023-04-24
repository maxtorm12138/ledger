package org.maxtorm.ledger.commodity.exception;

import lombok.Getter;
import org.maxtorm.ledger.bo.Commodity;
import org.slf4j.helpers.MessageFormatter;

@Getter
public class ConvertExchangeFailed extends Exception {
    public ConvertExchangeFailed(Commodity source, Commodity destination, String format, Object... args) {
        super(MessageFormatter.format(format, args).getMessage());
        this.source = source;
        this.destination = destination;
    }

    private final Commodity source;
    private final Commodity destination;
}
