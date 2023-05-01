//package org.maxtorm.ledger.service;
//
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import org.maxtorm.ledger.entity.transaction.Transaction;
//import org.maxtorm.ledger.entity.transaction.TransactionMapper;
//import org.maxtorm.ledger.entity.transaction.TransactionRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.NoSuchElementException;
//
//@Service
//@AllArgsConstructor
//public class TransactionService {
//    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
//    private AccountService accountService;
//    private TransactionRepository transactionRepository;
//
//    @Transactional(value = Transactional.TxType.REQUIRED)
//    public void transfer(Transaction transaction) {
//        // take snapshot
//        var initiatorBalance = accountService.getAccountBalance(transaction.getInitiatorAccountId(), transaction.getInitiatorCommodity()).orElseThrow();
//        var receiverBalance = accountService.getAccountBalance(transaction.getReceiverAccountId(), transaction.getReceiverCommodity()).orElseThrow();
//
//        initiatorBalance.setBookBalance(initiatorBalance.getBookBalance().subtract(transaction.getInitiatorAmount()));
//        initiatorBalance.setTotalOutflow(initiatorBalance.getTotalOutflow().add(transaction.getReceiverAmount()));
//        accountService.saveAccountBalance(initiatorBalance);
//
//        receiverBalance.setBookBalance(receiverBalance.getBookBalance().add(transaction.getReceiverAmount()));
//        receiverBalance.setTotalInflow(receiverBalance.getTotalInflow().add(transaction.getReceiverAmount()));
//        accountService.saveAccountBalance(receiverBalance);
//
//        var transactionPo = TransactionMapper.INSTANCE.convert(transaction);
//        transactionRepository.saveAndFlush(transactionPo);
//
//    }
//
//    @Transactional(value = Transactional.TxType.REQUIRED)
//    public void cancel(String referenceNumber) {
//        // get transactions
//        var transactions = transactionRepository.getTransactionPosByReferenceNumber(referenceNumber);
//        if (transactions.isEmpty()) {
//            throw new NoSuchElementException();
//        }
//
//
//    }
//}
