package org.maxtorm.ledger.bo;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class AccountTest {
    @Test
    public void toJson() throws JsonProcessingException {
        Account account = new Account();
        account.setMajorCommodity(Commodity.CurrencyCNY);
        account.getAccountExtraInfo().getFund().setUnrealizedGain(BigDecimal.valueOf(100));;
        account.getAccountExtraInfo().getFund().setCommodityOfAccount(Commodity.of("Fund.004011"));

        String json = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValueAsString(account);
        ObjectMapper objectMapper = new ObjectMapper();
        assertThat(objectMapper.readTree(json)).isEqualTo(objectMapper.readTree("""
            {
                "accountId" : "",
                "rootAccountId" : "",
                "parentAccountId" : "",
                "name" : "",
                "iconUrl" : "",
                "majorCommodity" : "Currency.CNY",
                "accountExtraInfo" : {
                  "fund" : {
                    "commodityOfAccount" : "Fund.004011",
                    "netWorth" : 0,
                    "marketValue" : 0,
                    "averageCostPrice" : 0,
                    "dilutedCostPrice" : 0,
                    "totalBuyInAmount" : 0,
                    "totalSelloutAmount" : 0,
                    "totalHoldingCost" : 0,
                    "unrealizedGain" : 100
                  }
                },
                "accountBalance" : [ ]
              }
            """));
    }
}
