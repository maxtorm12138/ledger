package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Account {
    private String accountId = "";
    private String parentAccountId = "";
    private String name = "";
    private String iconUrl = "";
    private Commodity majorCommodity = Commodity.Undefined;
    private AccountExtraInfo accountExtraInfo = new AccountExtraInfo();
    private List<AccountBalance> accountBalance = List.of();
}
