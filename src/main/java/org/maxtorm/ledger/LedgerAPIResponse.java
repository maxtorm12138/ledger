package org.maxtorm.ledger;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.maxtorm.ledger.error.ErrorCode;

import java.io.IOException;

class NullSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeEndObject();
    }
}

public record LedgerAPIResponse<T>(
        @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
        ErrorCode code,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        @JsonSerialize(nullsUsing = NullSerializer.class)
        T data
) {
    public LedgerAPIResponse(ErrorCode code) {
        this(code, code.name(), null);
    }

    public LedgerAPIResponse(ErrorCode code, String message) {
        this(code, message, null);
    }
}