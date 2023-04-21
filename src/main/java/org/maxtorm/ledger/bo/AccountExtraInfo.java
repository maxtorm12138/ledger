package org.maxtorm.ledger.bo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.ToString;
import org.maxtorm.ledger.util.LedgerDecimal;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;

@Getter
@Setter
public class AccountExtraInfo {
    @Converter(autoApply = true)
    public static class AccountExtraInfoConverter implements AttributeConverter<AccountExtraInfo, String> {
        @Override
        public String convertToDatabaseColumn(AccountExtraInfo accountExtraInfo) {
            try {
                return new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(accountExtraInfo);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", e).getMessage());
            }
        }

        @Override
        public AccountExtraInfo convertToEntityAttribute(String strAccountBalanceExtraInfo) {
            try {
                return new ObjectMapper().readValue(strAccountBalanceExtraInfo, AccountExtraInfo.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", e).getMessage());
            }
        }
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", e.getMessage()).getMessage());
        }
    }

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Debit {
        private String card_no_tail = "";
    }

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Credit {
        private String card_no_tail = "";
    }

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Security {

    }

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Fund {
        // 计价货币
        private Commodity commodityOfAccount = Commodity.Undefined;

        // 基金净值
        private BigDecimal netWorth = LedgerDecimal.ZERO;

        // 市值
        private BigDecimal marketValue = LedgerDecimal.ZERO;

        // 平均成本价
        private BigDecimal averageCostPrice = LedgerDecimal.ZERO;

        // 摊薄成本价
        private BigDecimal dilutedCostPrice = LedgerDecimal.ZERO;

        // 总买入
        private BigDecimal totalBuyInAmount = LedgerDecimal.ZERO;

        // 总卖出
        private BigDecimal totalSelloutAmount = LedgerDecimal.ZERO;

        // 持仓总成本
        private BigDecimal totalHoldingCost = LedgerDecimal.ZERO;

        // 未实现收益 = 市值 - 持仓总成本
        private BigDecimal unrealizedGain = LedgerDecimal.ZERO;
    }

    private AccountExtraInfo_Fund fund = null;
    private AccountExtraInfo_Debit debit = null;
}
