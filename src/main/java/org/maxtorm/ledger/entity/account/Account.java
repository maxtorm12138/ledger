package org.maxtorm.ledger.entity.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "icon id is required")
    private String icon;

    @NotNull
    private Commodity majorCommodity;

    @Builder.Default
    private List<AccountBalance> accountBalance = List.of();
}
