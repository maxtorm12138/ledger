package org.maxtorm.ledger.entity.commodity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CommodityJsonSerializer extends JsonSerializer<Commodity> {
    @Override
    public void serialize(Commodity commodity, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(commodity.getQualifiedName());
    }
}
