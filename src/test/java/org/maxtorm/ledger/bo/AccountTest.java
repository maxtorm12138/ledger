package org.maxtorm.ledger.bo;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class AccountTest {
    @Test
    public void toJson() throws JsonProcessingException {
        Account account = new Account();
        account.setMajorCommodity(Commodity.CurrencyCNY);
        account.getAccountExtraInfo().getFund().setUnrealizedGain(BigDecimal.valueOf(100));;

        String json = new ObjectMapper().writeValueAsString(account);
        assertThat(json).isEqualTo("""
            {"accountId":"","rootAccountId":"","parentAccountId":"","name":"","iconUrl":"","majorCommodity":"Currency.CNY.None","accountExtraInfo":{"fund":{"commodityOfAccount":"Undefined","netWorth":0,"marketValue":0,"averageCostPrice":0,"dilutedCostPrice":0,"costCommodity":"Currency.CNY.None","totalBuyInAmount":0,"totalSelloutAmount":0,"totalHoldingCost":0,"unrealizedGain":100}},"accountBalance":[]}
            """;);
    }
}
