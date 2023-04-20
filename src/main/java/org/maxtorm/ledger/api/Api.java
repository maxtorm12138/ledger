package org.maxtorm.ledger.api;

import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountTree;

import java.util.List;

public class Api {
    @Getter
    @Setter
    public static class OpenAccountRequest {
        Account account = new Account();
    }

    @Getter
    @Setter
    public static class OpenAccountResponse {
        Account account = new Account();
    }

    @Getter
    @Setter
    public static class GetAccountTreeRequest {
        String accountId = "";
    }

    @Getter
    @Setter
    public static class GetAccountTreeResponse {
        List<AccountTree> accountTree = List.of();
    }
}
