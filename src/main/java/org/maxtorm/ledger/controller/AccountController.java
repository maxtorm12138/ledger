package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    @PostMapping(value = "/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result<Api.OpenAccountResponse> open(@RequestBody Api.OpenAccountRequest request) {
        var response = new Api.OpenAccountResponse();

        var account = request.getAccount();
        var accountOpened = accountService.open(account);

        response.setAccount(accountOpened);
        return Result.success(response);
    }

    @PostMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result<Api.GetAccountTreeResponse> tree(@RequestBody Api.GetAccountTreeRequest request) {
        var accountTree = accountService.tree(request.getAccountId());
        var response = new Api.GetAccountTreeResponse();
        response.setAccountTree(accountTree);
        return Result.success(response);
    }
}
