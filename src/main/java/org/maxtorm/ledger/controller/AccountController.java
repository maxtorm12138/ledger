package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.ErrorCode;
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

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Api.CreateAccountResponse create(@RequestBody Api.CreateAccountRequest request) {
        logger.trace("request: {}", request);

        var responseBuilder = Api.CreateAccountResponse.newBuilder();
        var accountCreated = accountService.create(request.getAccount());

        responseBuilder.setAccount(accountCreated);
        responseBuilder.setErrorCode(ErrorCode.Success.ordinal());
        responseBuilder.setErrorMessage("success");

        final var response = responseBuilder.build();
        logger.trace("response: {}", response);

        return response;
    }

    @GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Api.GetAccountTreeResponse getTree(@RequestParam(required = false, name = "account_id", defaultValue = "") String accountId) {
        logger.trace("request: {}", accountId);
        var responseBuilder = Api.GetAccountTreeResponse.newBuilder();

        responseBuilder.addAllAccountTree(accountService.tree(accountId));

        final var response = responseBuilder.build();
        logger.trace("response: {}", response);

        return response;
    }
}
