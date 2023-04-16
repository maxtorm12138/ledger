package org.maxtorm.ledger.po;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

public class CommodityPoTest {
    @Test
    public void Stock() {
        CommodityPo commodityPo = new CommodityPo("Security.00700.HK");
        assertThat(commodityPo.toString()).isEqualTo("Security.00700.HK");

        commodityPo = new CommodityPo("Security.AAPL.US");
        assertThat(commodityPo.getName()).isEqualTo("AAPL");
    }

    @Test
    public void Fund() {
        CommodityPo commodityPo = new CommodityPo("Fund.012348");
        assertThat(commodityPo.toString()).isEqualTo("Fund.012348");
    }

    @Test
    public void Currency() {
        CommodityPo commodityPo = new CommodityPo("Currency.CNY");
        assertThat(commodityPo.getName()).isEqualTo("CNY");
    }
}
