package org.maxtorm.ledger.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.bo.AccountBalance;
import org.maxtorm.ledger.po.AccountBalancePo;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AccountBalanceMapper {
    AccountBalanceMapper INSTANCE = Mappers.getMapper(AccountBalanceMapper.class);

    AccountBalance convert(AccountBalancePo balancePo);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "accountBalanceId", ignore = true)
    AccountBalancePo convert(AccountBalance balance);

    List<AccountBalance> convertPosToBos(List<AccountBalancePo> balance);

    List<AccountBalancePo> convertBosToPos(List<AccountBalance> balance);
}
