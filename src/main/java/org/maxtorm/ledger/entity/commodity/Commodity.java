package org.maxtorm.ledger.entity.commodity;

import lombok.*;

import java.math.BigInteger;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Commodity {
    private String guid;
    private String namespace;
    private String mnemonic;
    private String fullName;
    private String quoteSource;
    private BigInteger fraction;

    public String getQualifiedName() {
        return String.join("::", namespace, mnemonic);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Commodity) {
            return getQualifiedName().equals(((Commodity) other).getQualifiedName());
        } else if (other instanceof String) {
            return getQualifiedName().equals(other);
        }

        return false;
    }

    public boolean equals(String other) {
        return getQualifiedName().equals(other);
    }
}


