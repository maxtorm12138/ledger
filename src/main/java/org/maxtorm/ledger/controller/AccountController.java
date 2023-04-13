package org.maxtorm.ledger.controller;

import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.service.FundService;

import org.maxtorm.ledger.dao.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(FundService.class);

    private AccountService accountService;

    @GetMapping("/create")
    @ResponseBody
    public Response<Account> create() {
        Response<Account> response = new Response<>();
        response.setData(accountService.create());
        return response;
    }
}
