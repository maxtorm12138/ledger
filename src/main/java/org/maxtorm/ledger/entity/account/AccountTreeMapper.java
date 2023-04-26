package org.maxtorm.ledger.entity.account;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AccountTreeMapper {
    AccountTreeMapper INSTANCE = Mappers.getMapper(AccountTreeMapper.class);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "accountBalance", ignore = true)
    AccountTree convert(AccountPo accountPo);

    @Mapping(target = "accountBalance", ignore = true)
    @Mapping(target = "children", ignore = true)
    AccountTree convert(Account account);
}
