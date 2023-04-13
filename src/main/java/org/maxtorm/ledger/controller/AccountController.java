package org.maxtorm.ledger.controller;

import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.service.FundService;

import org.maxtorm.ledger.obj.Account;

import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(FundService.class);

    private AccountService accountService;


    @Getter
    @Setter
    private static class AccountCreateRequest {
        private String name;
        private String iconUrl;
        private String parentAccountId;
        private String rootAccountId;
        private Integer depth;
    }

    @Getter
    @Setter
    private static class AccountCreateResponse {
        private Account account;
    }

    @PostMapping("/create")
    @ResponseBody
    public Result<AccountCreateResponse> create(@RequestBody AccountCreateRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setIconUrl(URI.create(request.iconUrl));
        account.setParentAccountId(request.getParentAccountId());
        account.setRootAccountId(request.getRootAccountId());
        account.setDepth(request.getDepth());

        account = accountService.create(account);

        AccountCreateResponse response = new AccountCreateResponse();
        response.setAccount(account);

        return Result.success(response);
    }
}
