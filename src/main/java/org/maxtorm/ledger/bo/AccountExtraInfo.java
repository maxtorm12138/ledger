package org.maxtorm.ledger.bo;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.maxtorm.ledger.util.NullObjectSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;

public class AccountExtraInfo {
    @Converter(autoApply = true)
    public static class AccountExtraInfoConverter implements AttributeConverter<AccountExtraInfo, String> {
        @Override
        public String convertToDatabaseColumn(AccountExtraInfo accountExtraInfo) {
            try {
                return new ObjectMapper().writeValueAsString(accountExtraInfo);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", accountExtraInfo).getMessage());
            }
        }

        @Override
        public AccountExtraInfo convertToEntityAttribute(String strAccountBalanceExtraInfo) {
            try {
                return new ObjectMapper().readValue(strAccountBalanceExtraInfo, AccountExtraInfo.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", strAccountBalanceExtraInfo).getMessage());
            }
        }
    }

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Fund {
        // 计价货币
        private Commodity commodityOfAccount = Commodity.Undefined;

        // 基金净值
        private BigDecimal netWorth = BigDecimal.valueOf(0L);

        // 市值
        private BigDecimal marketValue = BigDecimal.valueOf(0L);

        // 平均成本价
        private BigDecimal averageCostPrice = BigDecimal.valueOf(0L);

        // 摊薄成本价
        private BigDecimal dilutedCostPrice = BigDecimal.valueOf(0L);

        // 计价货币
        private Commodity costCommodity = Commodity.CurrencyCNY;

        // 总买入
        private BigDecimal totalBuyInAmount = BigDecimal.valueOf(0L);

        // 总卖出
        private BigDecimal totalSelloutAmount = BigDecimal.valueOf(0L);

        // 持仓总成本
        private BigDecimal totalHoldingCost = BigDecimal.valueOf(0L);

        // 未实现收益 = 市值 - 持仓总成本
        private BigDecimal unrealizedGain = BigDecimal.valueOf(0L);
    }

    @JsonSerialize(nullsUsing = NullObjectSerializer.class)
    private final AccountExtraInfo_Fund fund = null;
}