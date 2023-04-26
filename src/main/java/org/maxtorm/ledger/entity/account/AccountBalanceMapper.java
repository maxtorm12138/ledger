package org.maxtorm.ledger.entity.account;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AccountBalanceMapper {
    AccountBalanceMapper INSTANCE = Mappers.getMapper(AccountBalanceMapper.class);

    AccountBalance convert(AccountBalancePo balancePo);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "accountBalanceId", ignore = true)
    AccountBalancePo convert(AccountBalance balance);

    List<AccountBalance> convertPosToBos(List<AccountBalancePo> balance);

    List<AccountBalancePo> convertBosToPos(List<AccountBalance> balance);
}
