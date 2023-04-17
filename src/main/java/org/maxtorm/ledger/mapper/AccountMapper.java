package org.maxtorm.ledger.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.proto.Entity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = CommodityMapper.class)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "accountBalance", target = "accountBalanceList")
    Entity.Account Convert(AccountPo account);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(source = "accountBalanceList", target = "accountBalance")
    AccountPo Convert(Entity.Account account);
}
