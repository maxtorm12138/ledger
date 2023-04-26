package org.maxtorm.ledger.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.entity.account.Account;
import org.maxtorm.ledger.entity.account.AccountTree;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.commodity.Currency;
import org.maxtorm.ledger.entity.commodity.Fund;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Api {
    @Getter
    @Setter
    public static class OpenAccountRequest {
        @NotNull
        Account account;
    }

    @Getter
    @Setter
    public static class OpenAccountResponse {
    }

    @Getter
    @Setter
    public static class GetAccountTreeRequest {
        @NotBlank
        String accountId;
    }

    @Getter
    @Setter
    public static class GetAccountTreeResponse {
        AccountTree accountTree;
    }

    @Getter
    @Setter
    public static class GetHomeResponse {
        private boolean isInitialized;
    }

    @Getter
    @Setter
    public static class LedgerInitializeRequest {
        @NotNull
        Commodity majorCommodity;
    }

    @Getter
    @Setter
    public static class GetFundNavResponse {
        private Fund fund;
        private Currency currency;
        private BigDecimal nav;
        private LocalDate date;
    }

    @Getter
    @Setter
    public static class TransferRequest {
        @NotBlank
        private String initiatorAccountId;
        @NotBlank
        private String receiverAccountId;
        @NotNull
        private Commodity commodity;
        @NotNull
        private BigDecimal amount;
        @NotNull
        private String serviceChargePayer;
        @NotNull
        private BigDecimal serviceCharge;
        @NotNull
        private LocalDate initiateDate;
        @NotNull
        private String note;
    }
}
