package org.maxtorm.ledger.entity.commodity;

import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

public class CommodityBuilder {
    public static Commodity fromQualifiedName(String qualifiedName) {
        var matcher = Pattern.compile("^(?<category>[A-Za-z0-9]+).(?<name>[A-Za-z0-9]+)$").matcher(qualifiedName);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity: {}", qualifiedName).getMessage());
        }

        String category = matcher.group("category");

        return switch (category) {
            case Currency.categoryName -> new Currency(qualifiedName);
            case Stock.categoryName -> new Stock(qualifiedName);
            case Fund.categoryName -> new Fund(qualifiedName);
            default -> new Commodity(qualifiedName);
        };
    }
}
