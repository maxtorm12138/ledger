package org.maxtorm.ledger.entity.commodity;

import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

public class Fund extends Commodity {
    public static final String categoryName = "fund";

    public Fund(String qualifiedName) {
        var matcher = Pattern.compile("^fund.(?<name>[A-Za-z0-9]+)$").matcher(qualifiedName);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid fund: {}", qualifiedName).getMessage());
        }

        category = "fund";
        name = matcher.group("name");
    }

    public String getCode() {
        return name;
    }
}
