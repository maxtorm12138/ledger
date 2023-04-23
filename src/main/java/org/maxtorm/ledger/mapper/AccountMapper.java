package org.maxtorm.ledger.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.bo.Account;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.bo.AccountTree;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "accountBalance", ignore = true)
    Account convert(AccountPo account);

    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    AccountPo convert(Account account);

    List<Account> convertPosToBos(List<AccountPo> accountPoList);
}
