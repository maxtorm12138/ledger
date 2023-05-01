package org.maxtorm.ledger.entity.split;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maxtorm.ledger.entity.account.AccountPo;
import org.maxtorm.ledger.entity.transaction.TransactionPo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_transaction_split")
@Table(name = "user_transaction_split")
public class SplitPo {
    @Id
    @Column(name = "guid", nullable = false)
    private String guid;

    @OneToOne
    @JoinColumn(name = "account_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private AccountPo account;

    @Column(name = "memo", nullable = false)
    private String memo;

    @Column(name = "action", nullable = false)
    private String action;

    @OneToOne
    @JoinColumn(name = "tx_guid", referencedColumnName = "guid", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private TransactionPo transaction;
}
