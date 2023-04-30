package org.maxtorm.ledger.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.maxtorm.ledger.entity.commodity.Commodity;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String accountId;
    private String parentAccountId;
    private String name;
    private String icon;
    private Commodity majorCommodity;
    private List<AccountBalance> accountBalance;
    private String description;
}
