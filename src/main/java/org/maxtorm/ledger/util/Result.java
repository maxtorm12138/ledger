package org.maxtorm.ledger.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    ErrorCode errorCode = ErrorCode.Success;

    @Getter
    @Setter
    String errorCodeName = ErrorCode.Success.name();

    @Getter
    @Setter
    String message = errorCode.name();

    @Getter
    @Setter
    @JsonSerialize(nullsUsing = NullSerializer.class)
    T data = null;


    public static <T> Result<T> success(T data) {
        return new Result<>(ErrorCode.Success, ErrorCode.Success.name(), ErrorCode.Success.name(), data);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        return new Result<>(errorCode, errorCode.name(), errorCode.name(), null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, T data) {
        return new Result<>(errorCode, errorCode.name(), errorCode.name(), data);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, String message) {
        return new Result<>(errorCode, errorCode.name(), message, null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, String message, T data) {
        return new Result<>(errorCode, errorCode.name(), message, data);
    }

}
