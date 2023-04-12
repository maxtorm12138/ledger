package org.maxtorm.ledger.controller;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import org.maxtorm.ledger.error.ErrorCode;

public class Response<T> {

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    ErrorCode errorCode = ErrorCode.Success;

    @Getter
    @Setter
    String message = errorCode.name();

    @Getter
    @Setter
    @JsonSerialize(nullsUsing = ResponseNullSerializer.class)
    T data = null;
}
