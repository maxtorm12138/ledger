package org.maxtorm.ledger.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.entity.account.Account;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.LedgerConfig;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/portal")
public class PortalController {
    private static final Logger logger = LoggerFactory.getLogger(PortalController.class);
    private AccountService accountService;
    private LedgerConfig ledgerConfig;

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

        ledgerConfig.getAccountInitializeProperties().getAccounts().forEach((id, acc) -> {
            Account account = new Account();
            BeanUtils.copyProperties(acc, account);

            account.setAccountId(id);
            if (account.getParentAccountId() == null || account.getParentAccountId().isEmpty()) {
                account.setParentAccountId("system_root");
            }

            accountService.open(account);
        });

        return Result.success();
    }
}
