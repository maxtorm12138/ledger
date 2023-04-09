package org.maxtorm.ledger.error;

import org.maxtorm.ledger.LedgerAPIResponse;
import org.maxtorm.ledger.api.APIError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(APIError.class)
    @ResponseBody
    public LedgerAPIResponse handleApiError(Exception ex) {
        APIError e = (APIError) ex;
        return new LedgerAPIResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public LedgerAPIResponse handleException(Exception ex) {
        return new LedgerAPIResponse(ErrorCode.LogicError, ex.getMessage());
    }
}
