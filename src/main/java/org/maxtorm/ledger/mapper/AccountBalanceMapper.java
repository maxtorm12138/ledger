package org.maxtorm.ledger.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.AccountBalancePo;
import org.maxtorm.ledger.proto.Entity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = CommodityMapper.class)
public interface AccountBalanceMapper {
    AccountBalanceMapper INSTANCE = Mappers.getMapper(AccountBalanceMapper.class);

    Entity.AccountBalance Convert(AccountBalancePo balancePo);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    AccountBalancePo Convert(Entity.AccountBalance balance);
}
