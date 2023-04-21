package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.po.AccountPo;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account convert(AccountPo account);

    AccountPo convert(Account account);

    List<Account> convertPosToBos(List<AccountPo> accountPoList);
}
