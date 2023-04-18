package org.maxtorm.ledger.po;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Pattern;

@Convert(converter = CommodityPo.CommodityPoConverter.class)
@Getter
@Setter
public class CommodityPo {
    public enum Category {
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

    public static class CommodityPoConverter implements AttributeConverter<CommodityPo, String> {

        @Override
        public String convertToDatabaseColumn(CommodityPo commodityPo) {
            return commodityPo.toString();
        }

        @Override
        public CommodityPo convertToEntityAttribute(String commodity) {
            return new CommodityPo(commodity);
        }
    }

    private static final String commodityPattern = "^(?<category>[A-Za-z0-9]+)\\.(?<name>[A-Za-z0-9]+)(?:\\.(?<market>HK|US|CN|JP|SGP))?$";


    private Category category;
    private String name;
    private Market market = Market.None;

    public CommodityPo(String commodity) {
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
        } else {
            return String.join(".", getCategory().name(), getName());
        }
    }

    public static CommodityPo of(String commodity) {
        return new CommodityPo(commodity);
    }

    public static CommodityPo CurrencyCNY = CommodityPo.of("Currency.CNY");
    public static CommodityPo CurrencyHKD = CommodityPo.of("Currency.HKD");
    public static CommodityPo CurrencyUSD = CommodityPo.of("Currency.USD");
}


