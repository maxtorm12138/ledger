package org.maxtorm.ledger.po;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@AllArgsConstructor
@EqualsAndHashCode
public class AccountBalancePoId implements Serializable {
    private String accountId;
    private String commodity;
}
