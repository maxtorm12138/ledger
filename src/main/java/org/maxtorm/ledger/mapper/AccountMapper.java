package org.maxtorm.ledger.mapper;

import org.hibernate.mapping.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.po.AccountPo;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account Convert(AccountPo account);

    AccountPo Convert(Account account);

    List<Account> convert(List<AccountPo> account);
}
