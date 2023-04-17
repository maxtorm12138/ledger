package org.maxtorm.ledger.po;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;

import java.util.Optional;
import java.util.regex.Pattern;

@Convert(converter = CommodityPo.CommodityPoConverter.class)
@Getter
@Setter
public class CommodityPo {
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

    private static final String commodityPattern = "^(?<category>[A-Za-z0-9]+)\\.(?<name>[A-Za-z0-9]+)(?:\\.(?<market>HK|US|SH|SZ))?$";

    private String category;
    private String name;
    private String market;

    public CommodityPo(String commodity) {
        var regex = Pattern.compile(commodityPattern);
        var matcher = regex.matcher(commodity);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity {}", commodity).getMessage());
        }

        category = matcher.group("category");
        name = matcher.group("name");
        market = Optional.ofNullable(matcher.group("market")).orElse("");
    }

    @Override
    public String toString() {
        String str = String.format("%s.%s", category, name);
        return !market.isEmpty() ? String.format("%s.%s", str, market) : str;

    }

    public static CommodityPo of(String commodity) {
        return new CommodityPo(commodity);
    }

    public static CommodityPo CurrencyCNY = CommodityPo.of("Currency.CNY");
    public static CommodityPo CurrencyHKD = CommodityPo.of("Currency.HKD");
    public static CommodityPo CurrencyUSD = CommodityPo.of("Currency.USD");
}


