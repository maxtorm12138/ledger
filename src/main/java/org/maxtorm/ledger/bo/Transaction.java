package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NonNull
public class Transaction {
    private String referenceNumber = "";
    private String sourceAccountId = "";
    private Commodity sourceCommodity = Commodity.Undefined;
    private BigDecimal sourceAmount = BigDecimal.ZERO;
    private String destinationAccountId = "";
    private Commodity destinationCommodity = Commodity.Undefined;
    private BigDecimal destinationAmount = BigDecimal.ZERO;
    private TransactionExtraInfo transactionExtraInfo = new TransactionExtraInfo();
}
