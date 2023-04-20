package org.maxtorm.ledger.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

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
    @JsonSerialize(nullsUsing = NullObjectSerializer.class)
    T data = null;


    public static <T> Result<T> success(@NonNull T data) {
        return new Result<>(ErrorCode.Success, ErrorCode.Success.name(), ErrorCode.Success.name(), data);
    }

    public static <T> Result<T> fail(@NonNull ErrorCode errorCode) {
        return new Result<>(errorCode, errorCode.name(), errorCode.name(), null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, @NonNull T data) {
        return new Result<>(errorCode, errorCode.name(), errorCode.name(), data);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, @NonNull String message) {
        return new Result<>(errorCode, errorCode.name(), message, null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, String message, T data) {
        return new Result<>(errorCode, errorCode.name(), message, data);
    }
}
