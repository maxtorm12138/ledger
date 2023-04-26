package org.maxtorm.ledger.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.entity.account.Account;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/portal")
public class PortalController {
    private AccountService accountService;

    @GetMapping("/home")
    public @ResponseBody Result<Api.GetHomeResponse> home() {
        var response = new Api.GetHomeResponse();

        response.setInitialized(true);
        return Result.success(response);
    }

    @PostMapping("/initialize")
    public @ResponseBody Result<Void> initialize(@Valid @RequestBody Api.LedgerInitializeRequest request) {
        accountService.open(
                Account.builder()
                        .accountId("system_root")
                        .name("system_root")
                        .icon("system")
                        .parentAccountId("")
                        .majorCommodity(request.getMajorCommodity())
                        .build());

        accountService.open(
                Account.builder()
                        .accountId("user_account")
                        .name("user account")
                        .icon("system")
                        .parentAccountId("system_root")
                        .majorCommodity(request.getMajorCommodity())
                        .build());

        accountService.open(
                Account.builder()
                        .accountId("equity")
                        .name("equity")
                        .icon("system")
                        .parentAccountId("system_root")
                        .majorCommodity(request.getMajorCommodity())
                        .build());

        accountService.open(
                Account.builder()
                        .accountId("expenditure")
                        .name("expenditure")
                        .icon("system")
                        .parentAccountId("system_root")
                        .majorCommodity(request.getMajorCommodity())
                        .build());

        accountService.open(
                Account.builder()
                        .accountId("service_charge")
                        .name("service charge")
                        .icon("system")
                        .parentAccountId("expenditure")
                        .majorCommodity(request.getMajorCommodity())
                        .build());

        return Result.success();
    }
}
