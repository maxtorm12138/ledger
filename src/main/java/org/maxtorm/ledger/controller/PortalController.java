package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.bo.AccountTree;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/portal")
public class PortalController {
    private AccountService accountService;
}
