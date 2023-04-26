package org.maxtorm.ledger.entity.commodity;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

public class Stock extends Commodity {
    public static final String categoryName = "stock";

    @Getter
    private final String market;

    public Stock(String qualifiedName) {
        var matcher = Pattern.compile("^stock.(?<name>[A-Za-z1-9]+).(?<market>[A-Za-z0-9]+)$").matcher(qualifiedName);
        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid stock: {}", qualifiedName).getMessage());
        }

        name = matcher.group("name");
        market = matcher.group("market");
    }

    public String getQualifiedName() {
        return String.join(".", category, name, market);
    }

    public String getDisplayName() {
        return String.join(".", name, market);
    }
}
