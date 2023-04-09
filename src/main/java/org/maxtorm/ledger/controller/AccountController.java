package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.maxtorm.ledger.LedgerAPIResponse;
import org.maxtorm.ledger.dao.Account;
import org.maxtorm.ledger.dao.AccountRepository;
import org.maxtorm.ledger.error.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    private record AccountCreateRequest(String name) {}

    @PostMapping("create")
    public LedgerAPIResponse<Void> create(@RequestBody AccountCreateRequest request) {
        Account account = new Account();
        account.setName(request.name);
        accountRepository.save(account);
        return new LedgerAPIResponse<>(ErrorCode.Success);
    }

    @PostMapping("delete")
    public void delete() {

    }
}
