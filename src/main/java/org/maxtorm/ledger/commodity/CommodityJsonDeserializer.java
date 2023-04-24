package org.maxtorm.ledger.commodity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CommodityJsonDeserializer extends JsonDeserializer<Commodity> {

    @Override
    public Commodity deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        return CommodityBuilder.fromQualifiedName(p.getText());
    }
}
