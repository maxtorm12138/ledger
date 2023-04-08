package org.maxtorm.ledger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @PostMapping("create")
    public void create() {

    }

    @PostMapping("delete")
    public void delete() {

    }
}
