package org.maxtorm.ledger.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.po.AccountSummaryPo;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CommodityMapper.class)
public interface AccountSummaryMapper {
    AccountSummaryMapper INSTANCE = Mappers.getMapper(AccountSummaryMapper.class);

    Api.AccountSummary Convert(AccountSummaryPo account_summary);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    AccountSummaryPo Convert(Api.AccountSummary account_summary);
}
