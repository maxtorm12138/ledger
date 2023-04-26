package org.maxtorm.ledger.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.entity.account.AccountTree;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    @PostMapping(value = "/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result<Api.OpenAccountResponse> open(@Valid @RequestBody Api.OpenAccountRequest request) {
        var account = request.getAccount();
        accountService.open(account);

        var response = new Api.OpenAccountResponse();
        return Result.success(response);
    }

    @GetMapping(value = "tree")
    public @ResponseBody Result<Api.GetAccountTreeResponse> tree() {
        List<AccountTree> accountTreeList = accountService.tree("");
        var response = new Api.GetAccountTreeResponse();
        response.setAccountTree(accountTreeList.get(0));
        return Result.success(response);
    }

}
