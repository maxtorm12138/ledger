package org.maxtorm.ledger.po;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user_transaction_sequence")
@Table(name = "user_transaction_sequence")
public class TransactionSequenceNumberPo {
    @Id
    private Long sequence_number;
}
