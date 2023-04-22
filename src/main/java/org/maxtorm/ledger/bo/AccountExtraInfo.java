package org.maxtorm.ledger.bo;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.maxtorm.ledger.util.LedgerDecimal;
import org.slf4j.helpers.MessageFormatter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountExtraInfo {
    private AccountExtraInfo_Fund fund = null;
    private AccountExtraInfo_Debit debit = null;
    private AccountExtraInfo_Credit credit = null;
    private AccountExtraInfo_Security security = null;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(MessageFormatter.format("Error converting AccountBalanceExtraInfo: {}", e.getMessage()).getMessage());
        }
    }

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

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Debit {
        private String cardNoTail = "";
    }

    @Getter
    @Setter
    @NonNull
    public static class AccountExtraInfo_Credit {
        private String cardNoTail = "";
    }

    @Getter
    @Setter
    @NonNull
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class AccountExtraInfo_Security {
        private BigDecimal totalMarketValue = LedgerDecimal.ZERO;
        private BigDecimal totalCost = LedgerDecimal.ZERO;
        private BigDecimal unrealizedGain = LedgerDecimal.ZERO;
    }

    @Getter
    @Setter
    @NonNull
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class AccountExtraInfo_Fund {
        private BigDecimal totalMarketValue = LedgerDecimal.ZERO;
        private BigDecimal totalCost = LedgerDecimal.ZERO;
        private BigDecimal unrealizedGain = LedgerDecimal.ZERO;
    }
}
