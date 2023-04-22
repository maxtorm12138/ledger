package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.bo.AccountBalance;
import org.maxtorm.ledger.po.AccountBalancePo;

import java.util.List;

@Mapper
public interface AccountBalanceMapper {
    AccountBalanceMapper INSTANCE = Mappers.getMapper(AccountBalanceMapper.class);

    AccountBalance convert(AccountBalancePo balancePo);

    AccountBalancePo convert(AccountBalance balance);

    List<AccountBalance> convertPosToBos(List<AccountBalancePo> balance);

    List<AccountBalancePo> convertBosToPos(List<AccountBalance> balance);
}
