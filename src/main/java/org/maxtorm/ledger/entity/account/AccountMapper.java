package org.maxtorm.ledger.entity.account;

import org.hibernate.annotations.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.maxtorm.ledger.controller.AccountController;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account convertPoToBo(AccountPo accountPo);

    AccountPo convertBoToPo(Account account);
}
