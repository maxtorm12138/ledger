package org.maxtorm.ledger.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    @PostMapping(value = "/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result<Api.OpenAccountResponse> open(@Valid @RequestBody Api.OpenAccountRequest request) {
        var account = request.getAccount();
        account = accountService.open(account);

        var response = new Api.OpenAccountResponse();
        response.setAccount(account);
        return Result.success(response);
    }

}
