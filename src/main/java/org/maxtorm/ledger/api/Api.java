package org.maxtorm.ledger.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountTree;

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
        @NotNull
        Account account;
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
}
