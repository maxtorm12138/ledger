package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.Commodity;

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

    public Commodity getSourceCommodity() {
        return Commodity.of(sourceCommodity);
    }

    public void setSourceCommodity(Commodity sourceCommodity) {
        this.sourceCommodity = sourceCommodity.toString();
    }

    public Commodity getDestinationCommodity() {
        return Commodity.of(destinationCommodity);
    }

    public void setDestinationCommodity(Commodity destinationCommodity) {
        this.destinationCommodity = destinationCommodity.toString();
    }

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "price_date", nullable = false)
    private String priceDate;
}
