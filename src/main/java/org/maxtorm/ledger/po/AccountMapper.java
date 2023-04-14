package org.maxtorm.ledger.po;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.api.Api;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Api.Account Convert(AccountPo account);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    AccountPo Convert(Api.Account account);
}
