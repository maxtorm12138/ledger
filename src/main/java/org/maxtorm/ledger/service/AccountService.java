package org.maxtorm.ledger.service;

import lombok.AllArgsConstructor;

import org.maxtorm.ledger.dao.Account;
import org.maxtorm.ledger.dao.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(FundService.class);

    private AccountRepository accountRepository;


    public Account create() {
        Account account = new Account();
        account.setName("test");

        accountRepository.save(account);
        return account;
    }
}
