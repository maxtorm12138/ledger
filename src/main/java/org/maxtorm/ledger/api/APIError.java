package org.maxtorm.ledger.api;

import lombok.Getter;
import org.maxtorm.ledger.util.ErrorCode;
import org.slf4j.helpers.MessageFormatter;

public class APIError extends RuntimeException {
    APIError(ErrorCode errorCode, String format, Object ... objects) {
        super(MessageFormatter.format(format, objects).getMessage());
        this.errorCode = errorCode;
    }

    @Getter
    ErrorCode errorCode;
}
