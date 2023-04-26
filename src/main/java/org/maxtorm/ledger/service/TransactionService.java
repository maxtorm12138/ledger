package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.maxtorm.ledger.entity.transaction.TransactionMapper;
import org.maxtorm.ledger.entity.transaction.TransactionRepository;
import org.maxtorm.ledger.entity.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private AccountService accountService;
    private TransactionRepository transactionRepository;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void transfer(Transaction transaction) {
        // take snapshot
        var initiatorBalanceSnapshot = accountService.getAccountBalance(transaction.getInitiatorAccountId(), transaction.getInitiatorCommodity()).orElseThrow();
        var receiverBalanceSnapshot = accountService.getAccountBalance(transaction.getReceiverAccountId(), transaction.getReceiverCommodity()).orElseThrow();

        initiatorBalanceSnapshot.setBookBalance(initiatorBalanceSnapshot.getBookBalance().subtract(transaction.getInitiatorAmount()));
        initiatorBalanceSnapshot.setTotalOutflow(initiatorBalanceSnapshot.getTotalOutflow().add(transaction.getReceiverAmount()));
        transaction.setInitiatorBookBalanceSnapshot(initiatorBalanceSnapshot.getBookBalance());
        accountService.saveAccountBalance(initiatorBalanceSnapshot);

        receiverBalanceSnapshot.setBookBalance(receiverBalanceSnapshot.getBookBalance().add(transaction.getReceiverAmount()));
        receiverBalanceSnapshot.setTotalInflow(receiverBalanceSnapshot.getTotalInflow().add(transaction.getReceiverAmount()));
        transaction.setReceiverBookBalanceSnapshot(receiverBalanceSnapshot.getBookBalance());
        accountService.saveAccountBalance(receiverBalanceSnapshot);

        var transactionPo = TransactionMapper.INSTANCE.convert(transaction);
        transactionPo = transactionRepository.save(transactionPo);

    }
}
