package org.maxtorm.ledger.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.TransactionPo;
import org.maxtorm.ledger.proto.Entity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CommodityMapper.class)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Entity.Transaction Convert(TransactionPo account);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    TransactionPo Convert(Entity.Transaction account);
}
