package org.maxtorm.ledger.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.maxtorm.ledger.util.LedgerDecimal;

import java.math.BigDecimal;

@Getter
@Setter
@NonNull
public class Transaction {
    @NotBlank
    private String referenceNumber = "";
    @NotBlank
    private String sourceAccountId = "";
    private Commodity sourceCommodity = Commodity.Undefined;
    private BigDecimal sourceCommodityAmount = LedgerDecimal.ZERO;
    @NotBlank
    private String destinationAccountId = "";
    private Commodity destinationCommodity = Commodity.Undefined;
    private BigDecimal destinationCommodityAmount = LedgerDecimal.ZERO;
    private ExtraInfoTag extraInfoTag = ExtraInfoTag.Undefined;
    private TransactionExtraInfo transactionExtraInfo = new TransactionExtraInfo();
    private TransactionState transactionState = TransactionState.Undefined;
    public enum ExtraInfoTag {
        Undefined,
        Transfer,
        CurrencyExchange
    }

    public enum TransactionState {
        Undefined,
        Created,
        Confirmed,
        Canceled
    }
}
