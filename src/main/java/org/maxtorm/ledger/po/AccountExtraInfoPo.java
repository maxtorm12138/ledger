package org.maxtorm.ledger.po;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.AttributeConverter;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.util.NullObjectSerializer;
import org.slf4j.helpers.MessageFormatter;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AccountExtraInfoPo {
    public static class AccountExtraInfoConverter implements AttributeConverter<AccountExtraInfoPo, String> {

        @Override
        public String convertToDatabaseColumn(AccountExtraInfoPo accountExtraInfoPo) {
            try {
                return new ObjectMapper().writeValueAsString(accountExtraInfoPo);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", accountExtraInfoPo).getMessage());
            }
        }

        @Override
        public AccountExtraInfoPo convertToEntityAttribute(String strAccountBalanceExtraInfo) {
            try {
                return new ObjectMapper().readValue(strAccountBalanceExtraInfo, AccountExtraInfoPo.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", strAccountBalanceExtraInfo).getMessage());
            }
        }
    }


    @Getter
    @Setter
    @NonNull
    public static class Fund {
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
    Fund fund = null;
}
