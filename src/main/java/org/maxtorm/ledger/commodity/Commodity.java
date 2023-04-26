package org.maxtorm.ledger.commodity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

import java.util.regex.Pattern;

@JsonSerialize(using = CommodityJsonSerializer.class)
@JsonDeserialize(using = CommodityJsonDeserializer.class)
public class Commodity {
    @Getter
    protected String category;
    protected String name;

    protected Commodity() {
    }

    public Commodity(String qualifiedName) {
        var matcher = Pattern.compile("^(?<category>[A-Za-z0-9]+).(?<name>[A-Za-z0-9]+)$").matcher(qualifiedName);

        if (!matcher.find()) {
            throw new IllegalArgumentException(MessageFormatter.format("invalid commodity: {}", qualifiedName).getMessage());
        }

        category = matcher.group("category");
        name = matcher.group("name");
    }

    public String getQualifiedName() {
        return String.join(".", category, name);
    }

    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Commodity) {
            return getQualifiedName().equals((((Commodity) other).getQualifiedName()));
        }

        return false;
    }
}


