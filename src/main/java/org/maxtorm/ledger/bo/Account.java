package org.maxtorm.ledger.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Account {
    private String accountId = "";
    @NotNull
    private String parentAccountId = "";

    @NotBlank(message = "name is required")
    private String name = "";
    @NotBlank(message = "category is required")
    private String category;
    @NotBlank(message = "icon id is required")
    private String iconId = "";
    private Commodity majorCommodity = Commodity.Undefined;
    private AccountExtraInfo extraInfo;
    private List<AccountBalance> accountBalance;
}
