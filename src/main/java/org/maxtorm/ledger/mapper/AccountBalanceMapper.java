package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.maxtorm.ledger.bo.AccountBalance;
import org.maxtorm.ledger.po.AccountBalancePo;

@Mapper
public interface AccountBalanceMapper {
    AccountBalanceMapper INSTANCE = Mappers.getMapper(AccountBalanceMapper.class);

    AccountBalance Convert(AccountBalancePo balancePo);

    AccountBalancePo Convert(AccountBalance balance);
}
