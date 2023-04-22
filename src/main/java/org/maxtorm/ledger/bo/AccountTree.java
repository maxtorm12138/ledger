package org.maxtorm.ledger.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountTree {
    private String name = "";
    private Account account = new Account();
    private List<AccountTree> children = List.of();
}
