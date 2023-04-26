package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.commodity.Commodity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Transaction {
    private Long sequenceNumber;
    private String referenceNumber;
    private String category;
    private LocalDate initiateDate;
    private TransactionState transactionState;
    private String note;
    private String initiatorAccountId;
    private String receiverAccountId;
    private Commodity initiatorCommodity;
    private Commodity receiverCommodity;
    private BigDecimal initiatorAmount;
    private BigDecimal receiverAmount;
    private BigDecimal initiatorBookBalanceSnapshot;
    private BigDecimal receiverBookBalanceSnapshot;
}
