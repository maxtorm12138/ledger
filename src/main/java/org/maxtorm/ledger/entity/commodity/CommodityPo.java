package org.maxtorm.ledger.entity.commodity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maxtorm.ledger.entity.AbstractTimestampEntity;

import java.math.BigInteger;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "commodity")
@Table(name = "commodity", indexes = @Index(name = "unique_namespace_mnemonic", columnList = "namespace, mnemonic", unique = true))
public class CommodityPo extends AbstractTimestampEntity {
    @Id
    private String guid;

    @Column(name = "namespace", nullable = false)
    private String namespace;

    @Column(name = "mnemonic", nullable = false)
    private String mnemonic;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "quote_source", nullable = false)
    private String quoteSource;

    @Column(name = "fraction", nullable = false)
    private BigInteger fraction;
}
