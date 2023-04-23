package org.maxtorm.ledger.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Builder.Default
    private String accountId = "";
    @NotNull
    @Builder.Default
    private String parentAccountId = "";
    @NotBlank(message = "name is required")
    @Builder.Default
    private String name = "";
    @NotBlank(message = "category is required")
    @Builder.Default
    private String category = "";
    @NotBlank(message = "icon id is required")
    @Builder.Default
    private String iconId = "account.svg";
    @Builder.Default
    private Commodity majorCommodity = Commodity.Undefined;
    @Builder.Default
    private AccountExtraInfo extraInfo = new AccountExtraInfo();
    @Builder.Default
    private List<AccountBalance> accountBalance = new ArrayList<>();
}
