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
    @Column(name = "commodity_price_id", nullable = false)
    private String commodityPriceId;

    @Column(name = "source_commodity", nullable = false)
    private String sourceCommodity;

    @Column(name = "destination_commodity", nullable = false)
    private String destinationCommodity;

    public CommodityPo getSourceCommodity() {
        return CommodityPo.of(sourceCommodity);
    }

    public void setSourceCommodity(CommodityPo sourceCommodity) {
        this.sourceCommodity = sourceCommodity.toString();
    }

    public CommodityPo getDestinationCommodity() {
        return CommodityPo.of(destinationCommodity);
    }

    public void setDestinationCommodity(CommodityPo destinationCommodity) {
        this.destinationCommodity = destinationCommodity.toString();
    }

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "price_date", nullable = false)
    private String priceDate;
}
