package org.maxtorm.ledger.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FundService {
    private static final Logger logger = LoggerFactory.getLogger(FundService.class);

    @Transactional(Transactional.TxType.SUPPORTS)
    public void subscribe(String code) {

    }
}
