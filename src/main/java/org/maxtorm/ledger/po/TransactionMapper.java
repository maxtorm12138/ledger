package org.maxtorm.ledger.po;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.api.Api;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Api.Transaction Convert(TransactionPo account);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    TransactionPo Convert(Api.Transaction account);
}
