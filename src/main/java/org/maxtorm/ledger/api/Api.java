package org.maxtorm.ledger.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountTree;

import java.util.List;

public class Api {
    @Builder
    @Getter
    @Setter
    public static class OpenAccountRequest {
        Account account = new Account();
    }

    @Builder
    @Getter
    @Setter
    public static class OpenAccountResponse {
        Account account = new Account();
    }

    @Builder
    @Getter
    @Setter
    public static class GetAccountTreeRequest {
        String accountId = "";
    }

    @Builder
    @Getter
    @Setter
    public static class GetAccountTreeResponse {
        List<AccountTree> accountTree = List.of();
    }
}
