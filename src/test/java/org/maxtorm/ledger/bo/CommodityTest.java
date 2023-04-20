package org.maxtorm.ledger.bo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommodityTest {
    @Test
    public void Security() {
        Commodity commodity = new Commodity("Security.00700.HK");
        assertThat(commodity.toString()).isEqualTo("Security.00700.HK");

        commodity = new Commodity("Security.AAPL.US");
        assertThat(commodity.getName()).isEqualTo("AAPL");
    }

    @Test
    public void invalidSecurity() {
        try {
            Commodity commodity = new Commodity("Security.AAPL.HK");
            assertThat(false).isTrue();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void fund() {
        Commodity commodity = new Commodity("Fund.012348");
        assertThat(commodity.toString()).isEqualTo("Fund.012348");
    }

    @Test
    public void currency() {
        Commodity commodity = new Commodity("Currency.CNY");
        assertThat(commodity.getName()).isEqualTo("CNY");
    }
}
