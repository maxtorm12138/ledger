package org.maxtorm.ledger.controller;


import org.junit.jupiter.api.Test;
import org.maxtorm.ledger.proto.Api;
import org.maxtorm.ledger.proto.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;
    @Test
    public void open() {
        var requestBuilder = Api.CreateAccountRequest.newBuilder();
        var accountBuilder = Entity.Account.newBuilder();
        accountBuilder.setName("root");
        requestBuilder.setAccount(accountBuilder);

        var response = accountController.open(requestBuilder.build());
        assertThat(response.getErrorCode()).isEqualTo(0);
    }
}
