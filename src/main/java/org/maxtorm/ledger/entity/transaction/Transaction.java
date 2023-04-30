package org.maxtorm.ledger.entity.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.maxtorm.ledger.entity.commodity.Commodity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
}
