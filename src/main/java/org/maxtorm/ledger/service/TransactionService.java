package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.bo.Transaction;
import org.maxtorm.ledger.repository.TransactionRepository;
import org.maxtorm.ledger.mapper.TransactionMapper;
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
        var transactionPo = TransactionMapper.INSTANCE.convert(transaction);

        var sourceAccount = accountService.getAccount(transaction.getSourceAccountId()).orElseThrow();
        var destinationAccount = accountService.getAccount(transaction.getDestinationAccountId()).orElseThrow();

        transactionPo = transactionRepository.save(transactionPo);

        TransactionMapper.INSTANCE.convert(transactionPo);
    }
}
