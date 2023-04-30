package org.maxtorm.ledger.entity.commodity;

import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

public class Currency extends Commodity {
    public static final String NAMESPACE = "currency";
    public static final Currency HKD = new Currency("currency.HKD");
    public static final Currency CNY = new Currency("currency.CNY");
    public static final Currency USD = new Currency("currency.USD");

    public Currency(String qualifiedName) {
        var matcher = Pattern.compile("^currency.(?<name>[A-Z]+)$").matcher(qualifiedName);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid currency: {}", qualifiedName).getMessage());
        }

        namespace = "currency";
        name = matcher.group("name");
    }

}
