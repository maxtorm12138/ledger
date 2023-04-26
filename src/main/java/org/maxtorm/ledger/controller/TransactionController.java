package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;

import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.entity.transaction.Transaction;
import org.maxtorm.ledger.entity.transaction.TransactionState;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.service.TransactionService;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private TransactionService transactionService;
    private AccountService accountService;

    @PostMapping("/transfer")
    public @ResponseBody Result<Void> transfer(@RequestBody Api.TransferRequest request) {
        // prepare balance
        accountService.openBalance(request.getInitiatorAccountId(), request.getCommodity());
        accountService.openBalance(request.getReceiverAccountId(), request.getCommodity());

        var transaction = Transaction.builder()
                .sequenceNumber(0L)
                .referenceNumber(UUID.randomUUID().toString())
                .category("transfer")
                .initiateDate(request.getInitiateDate())
                .transactionState(TransactionState.Preparing)
                .note(request.getNote())
                .initiatorAccountId(request.getInitiatorAccountId())
                .receiverAccountId(request.getReceiverAccountId())
                .initiatorCommodity(request.getCommodity())
                .receiverCommodity(request.getCommodity())
                .initiatorAmount(request.getAmount())
                .receiverAmount(request.getAmount())
                .build();

        transactionService.transfer(transaction);

        return Result.success();
    }
}
