package org.maxtorm.ledger.entity.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTree extends Account {
    @Builder.Default
    private List<AccountTree> children = List.of();
}
