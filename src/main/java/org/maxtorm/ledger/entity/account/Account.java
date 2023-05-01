package org.maxtorm.ledger.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.maxtorm.ledger.entity.commodity.Commodity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class Account {
    private String guid;
    private String parentGuid;
    private boolean placeholder;
    private String name;
    private String description;
    private String type;
    private String icon;
    private Commodity majorCommodity;
}
