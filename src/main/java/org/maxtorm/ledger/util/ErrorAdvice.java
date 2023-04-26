package org.maxtorm.ledger.util;

import org.maxtorm.ledger.exception.OpenAccountAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Result<Void>> handleHttpRequestMethodNotSupportedException(Exception ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(Result.fail(ErrorCode.MethodNotSupported, ex.getMessage()));
    }

    @ExceptionHandler(OpenAccountAlreadyExistException.class)
    public ResponseEntity<Result<Void>> handleAccountAlreadyExistException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.fail(ErrorCode.OpenAccountAlreadyExists, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result<Void>> handleHttpMessageNotReadableException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.fail(ErrorCode.LogicError, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result<Void>> handleIllegalArgumentException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.fail(ErrorCode.IllegalArgument, ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Result<Void>> handleNoSuchElementException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.fail(ErrorCode.NoSuchElement, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.fail(ErrorCode.UnhandledException, ex.getMessage()));
    }
}
