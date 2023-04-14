package org.maxtorm.ledger.controller;

import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.ErrorCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.MediaType;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import org.maxtorm.ledger.api.Api;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    private TransactionTemplate transactionTemplate;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Api.AccountCreateResponse create(@RequestBody Api.AccountCreateRequest request) {
        logger.debug("request: {}", request);
        // start transaction
        return transactionTemplate.execute(status -> {
            Api.AccountCreateResponse.Builder responseBuilder = Api.AccountCreateResponse.newBuilder();
            Api.Account accountToCreate = request.getAccount();

            Optional<Api.Account> rootAccount = Optional.empty(), parentAccount = Optional.empty();

            // search root account
            if (!accountToCreate.getRootAccountId().isEmpty()) {
                rootAccount = accountService.getByAccountId(accountToCreate.getRootAccountId());
                logger.debug("find root account: {}", rootAccount.orElseThrow(() -> new IllegalArgumentException(MessageFormatter.format("rootAccountId {} invalid", accountToCreate.getRootAccountId()).getMessage())));
            }

            if (!accountToCreate.getParentAccountId().isEmpty()) {
                parentAccount = accountService.getByAccountId(accountToCreate.getParentAccountId());
                logger.debug("find root account: {}", parentAccount.orElseThrow(() -> new IllegalArgumentException(MessageFormatter.format("parentAccountId {} invalid", accountToCreate.getRootAccountId()).getMessage())));
            }

            Api.Account accountCreated = accountService.create(accountToCreate, rootAccount, parentAccount);

            responseBuilder.setAccount(accountCreated);
            responseBuilder.setErrorCode(ErrorCode.Success.ordinal());
            responseBuilder.setErrorMessage("success");


            return responseBuilder.build();
        });
    }
}
