package org.maxtorm.ledger.po;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AccountBalancePoId implements Serializable {
    private String accountId;
    private String commodity;
}
