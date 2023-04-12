package org.maxtorm.ledger.error;

import org.maxtorm.ledger.controller.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleException(Exception ex) {
        Response<Void> responseBody = new Response<>();
        responseBody.setErrorCode(ErrorCode.LogicError);
        responseBody.setMessage(ex.getMessage());

        return ResponseEntity.internalServerError().body(responseBody);
    }
}
