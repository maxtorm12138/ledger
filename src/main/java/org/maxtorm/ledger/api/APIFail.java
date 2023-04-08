package org.maxtorm.ledger.api;

import lombok.Getter;

import java.util.Optional;

public class APIFail extends Exception {
    APIFail(String message) {
        super(message);
    }

    APIFail(String message, int code) {
        super(message);
        this.code = Optional.of(code);
    }

    @Getter
    private Optional<Integer> code;
}
