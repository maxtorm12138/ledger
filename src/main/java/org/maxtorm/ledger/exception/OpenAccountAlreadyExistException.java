package org.maxtorm.ledger.exception;

public class OpenAccountAlreadyExistException extends RuntimeException {
    public OpenAccountAlreadyExistException(String message) {
        super(message);
    }
}
