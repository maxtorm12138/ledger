package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity(name = "account_balance")
@Table(name = "account_balance", indexes = {@Index(name = "unique_account_id_commodity", columnList = "account_id, commodity")})
public class AccountBalancePo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_summary_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountSummaryId = "";

    @Column(name = "account_id")
    private String accountId = "";

    @Column(name = "commodity")
    @Convert(converter = CommodityPo.CommodityPoConverter.class)
    private CommodityPo commodity;

    @Column(name = "amount")
    private Long amount = 0L;

}
