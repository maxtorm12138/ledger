package org.maxtorm.ledger.commodity;

import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

public class CommodityBuilder {
    public static Commodity fromQualifiedName(String qualifiedName) {
        var matcher = Pattern.compile("^(?<category>[A-Za-z0-9]+).(?<name>[A-Za-z0-9]+)$").matcher(qualifiedName);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity: {}", qualifiedName).getMessage());
        }

        String category = matcher.group("category");

        Commodity commodity;
        switch (category) {
            case Currency.categoryName:
                commodity = new Currency(qualifiedName);
                break;
            case Stock.categoryName:
                commodity = new Stock(qualifiedName);
                break;
            case Fund.categoryName:
                commodity = new Fund(qualifiedName);
                break;
            default:
                commodity = new Commodity(qualifiedName);
                break;
        }
        return commodity;
    }
}
