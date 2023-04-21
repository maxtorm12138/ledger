package org.maxtorm.ledger.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Account {
    private String accountId = "";
    private String parentAccountId = "";
    @NotBlank(message = "name is required")
    private String name = "";

    @NotBlank(message = "icon id is required")
    private String iconId = "";
    private Commodity majorCommodity = Commodity.Undefined;
    private AccountExtraInfo accountExtraInfo = new AccountExtraInfo();
    private List<AccountBalance> accountBalance = List.of();
}
