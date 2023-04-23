package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.bo.AccountTree;
import org.maxtorm.ledger.po.AccountPo;

@Mapper
public interface AccountTreeMapper {
    AccountTreeMapper INSTANCE = Mappers.getMapper(AccountTreeMapper.class);
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "accountBalance", ignore = true)
    AccountTree convert(AccountPo accountPo);

    @Mapping(target = "accountBalance", ignore = true)
    @Mapping(target = "children", ignore = true)
    AccountTree convert(Account account);
}
