package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.*;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.util.LedgerDecimal;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@IdClass(AccountBalancePoId.class)
@Entity(name = "account_balance")
@Table(name = "account_balance")
public class AccountBalancePo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_id")
    private String accountId = "";


    @Id
    @Column(name = "commodity")
    private String commodity = Commodity.Undefined.toString();

    public Commodity getCommodity() {
        return Commodity.of(commodity);
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity.toString();
    }

    @Column(name = "book_balance", precision = 34, scale = 5)
    private BigDecimal bookBalance = LedgerDecimal.ZERO;
}
