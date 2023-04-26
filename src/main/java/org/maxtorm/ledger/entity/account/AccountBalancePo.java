package org.maxtorm.ledger.entity.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.commodity.CommodityAttributeConverter;
import org.maxtorm.ledger.util.AbstractTimestampEntity;

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
    @Convert(converter = CommodityAttributeConverter.class)
    private Commodity commodity;

    @Column(name = "book_balance", precision = 34, scale = 5, nullable = false)
    private BigDecimal bookBalance;

    @Column(name = "total_inflow", precision = 34, scale = 5, nullable = false)
    private BigDecimal totalInflow;

    @Column(name = "total_outflow", precision = 34, scale = 5, nullable = false)
    private BigDecimal totalOutflow;

    @Column(name = "uncommitted_amount", precision = 34, scale = 5, nullable = false)
    private BigDecimal uncommittedAmount;
}
