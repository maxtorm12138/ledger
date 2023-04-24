package org.maxtorm.ledger.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.commodity.Commodity;
import org.maxtorm.ledger.util.LedgerDecimal;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "account_balance")
@Table(name = "account_balance")
public class AccountBalancePo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_balance_id", nullable = false)
    private String accountBalanceId;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "commodity", nullable = false)
    private Commodity commodity;

    @Column(name = "book_balance", precision = 34, scale = 5)
    private BigDecimal bookBalance = LedgerDecimal.ZERO;

    @Column(name = "total_inflow", precision = 34, scale = 5)
    private BigDecimal totalInflow = LedgerDecimal.ZERO;

    @Column(name = "total_outflow", precision = 34, scale = 5)
    private BigDecimal totalOutflow = LedgerDecimal.ZERO;

    @Column(name = "uncommitted_amount", precision = 34, scale = 5)
    private BigDecimal unCommittedAmount = LedgerDecimal.ZERO;

}
