package org.maxtorm.ledger.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.maxtorm.ledger.commodity.Commodity;

import java.math.BigDecimal;

@Getter
@Setter
@NonNull
public class Transaction {
    @NotBlank
    private String referenceNumber;

    @NotBlank
    private String sourceAccountId;

    @NotNull
    private Commodity sourceCommodity;

    @NotNull
    private BigDecimal sourceCommodityAmount;

    @NotBlank
    private String destinationAccountId;

    @NotNull
    private Commodity destinationCommodity;

    @NotNull
    private BigDecimal destinationCommodityAmount;

    private TransactionState transactionState;
}
