package org.maxtorm.ledger.bo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@Getter
@JsonSerialize(using = Commodity.CommodityJsonSerializer.class)
@JsonDeserialize(using = Commodity.CommodityJsonDeserializer.class)
public class Commodity {
    public static final Commodity Undefined = Commodity.of("Undefined");
    public static final Commodity CurrencyCNY = Commodity.of("Currency.CNY");
    public static final Commodity CurrencyHKD = Commodity.of("Currency.HKD");
    public static final Commodity CurrencyUSD = Commodity.of("Currency.USD");
    private Category category = Category.Undefined;
    private String name = "";
    private Market market = Market.None;
    public Commodity(String commodity) {
        if (commodity.equals(Category.Undefined.name())) {
            return;
        }

        var regex = Pattern.compile("(?<category>[A-Za-z0-9]+)\\.(?<name>[A-Za-z0-9]+)(?:\\.(?<market>HK|US|CN|JP|SGP))?$");
        var matcher = regex.matcher(commodity);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity {} pattern not match", commodity).getMessage());
        }

        category = Category.valueOf(matcher.group("category"));


        name = matcher.group("name");
        String strMarket = Optional.ofNullable(matcher.group("market")).orElse("");

        if (category != Category.Security && !strMarket.isEmpty()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity {}, market should not appear ", commodity).getMessage());
        } else if (category == Category.Security) {
            if (strMarket.isEmpty()) {
                throw new IllegalArgumentException(MessageFormatter.format("invalid commodity {}, market required", commodity).getMessage());
            }
            market = Market.valueOf(strMarket);
        }
    }

    public static Commodity of(String commodity) {
        return new Commodity(commodity);
    }

    @Override
    public String toString() {
        if (category == Category.Undefined) {
            return getCategory().name();
        } else if (category == Category.Security) {
            return String.join(".", getCategory().name(), getName(), getMarket().name());
        } else {
            return String.join(".", getCategory().name(), getName());
        }
    }

    public String toQualifiedString() {
        if (category == Category.Security) {
            return String.join(".", getName(), getMarket().name());
        }

        return getName();
    }

    public enum Category {
        Undefined,
        Security,
        Currency,
        Fund,
        Bond,
        Other
    }

    public enum Market {
        None,
        CN,
        HK,
        US,
        JP,
        SGP
    }

    @Converter(autoApply = true)
    public static class CommodityConverter implements AttributeConverter<Commodity, String> {

        @Override
        public String convertToDatabaseColumn(Commodity commodity) {
            return commodity.toString();
        }

        @Override
        public Commodity convertToEntityAttribute(String commodity) {
            return Commodity.of(commodity);
        }
    }

    public static class CommodityJsonSerializer extends JsonSerializer<Commodity> {

        @Override
        public void serialize(Commodity commodity, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(commodity.toString());
        }
    }

    public static class CommodityJsonDeserializer extends JsonDeserializer<Commodity> {

        @Override
        public Commodity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Commodity.of(p.getText());
        }
    }
}


