package org.maxtorm.ledger.controller;


import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.api.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;
    @Test
    public void create() {
        var requestBuilder = Api.CreateAccountRequest.newBuilder();
        var accountBuilder = Api.Account.newBuilder();
        accountBuilder.setDepth(0);
        accountBuilder.setName("root");
        requestBuilder.setAccount(accountBuilder);

        var response = accountController.create(requestBuilder.build());
        assertThat(response.getErrorCode()).isEqualTo(0);
        var root = response.getAccount();
    }
}
