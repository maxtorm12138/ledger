package org.maxtorm.ledger.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private AccountService accountService;
}
