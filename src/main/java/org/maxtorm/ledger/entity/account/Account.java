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
    private String name;
    private String icon;
    private Commodity majorCommodity;
    private List<AccountBalance> accountBalance;
}
