package org.maxtorm.ledger.bo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Getter
public class Commodity {
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

    public static class CommodityConverter implements AttributeConverter<Commodity, String> {

        @Override
        public String convertToDatabaseColumn(Commodity commodity) {
            return commodity.toString();
        }

        @Override
        public Commodity convertToEntityAttribute(String commodity) {
            return new Commodity(commodity);
        }
    }

    private static final String commodityPattern = "(?<category>[A-Za-z0-9]+)\\.(?<name>[A-Za-z0-9]+)(?:\\.(?<market>HK|US|CN|JP|SGP))?$";


    private Category category = Category.Undefined;
    private String name = "";
    private Market market = Market.None;

    public Commodity(String commodity) {
        if (Objects.equals(commodity, Category.Undefined.name())) {
            return;
        }

        var regex = Pattern.compile(commodityPattern);
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

        if ((market == Market.CN || market == Market.HK) && !name.matches("^[0-9]+$")) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity {}, invalid name", commodity).getMessage());
        }
    }

    @Override
    public String toString() {
        if (category == Category.Security) {
            return String.join(".", getCategory().name(), getName(), getMarket().name());
        } else if (category == Category.Undefined) {
          return getCategory().name();
        } else {
            return String.join(".", getCategory().name(), getName());
        }
    }

    public static Commodity of(String commodity) {
        return new Commodity(commodity);
    }

    public static Commodity Undefined = Commodity.of("Undefined");
    public static Commodity CurrencyCNY = Commodity.of("Currency.CNY");
    public static Commodity CurrencyHKD = Commodity.of("Currency.HKD");
    public static Commodity CurrencyUSD = Commodity.of("Currency.USD");
}


