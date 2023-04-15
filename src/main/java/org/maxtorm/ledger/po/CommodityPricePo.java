package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "commodity_price")
@Table(name = "commodity_price", indexes = {@Index(name = "unique_commodity_convert", columnList = "source_commodity, destination_commodity", unique = true)})
public class CommodityPricePo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "commodity_price_id")
    private String commodityPriceId;

    @Column(name = "source_commodity")
    private String sourceCommodity;

    @Column(name = "destination_commodity")
    private String destinationCommodity;

    @Column(name = "price")
    private Long price;
}
