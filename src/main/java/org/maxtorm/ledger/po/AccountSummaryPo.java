package org.maxtorm.ledger.po;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity(name = "account_summary")
@Table(name = "account_summary", indexes = {@Index(name = "unique_account_id_commodity", columnList = "account_id, commodity")})
public class AccountSummaryPo extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_summary_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountSummaryId = "";

    @Column(name = "account_id")
    private String accountId = "";

    @Column(name = "commodity")
    private String commodity = "";

    @Column(name = "amount")
    private Long amount = 0L;
}
