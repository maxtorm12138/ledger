package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;

import org.maxtorm.ledger.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private TransactionService transactionService;
}
