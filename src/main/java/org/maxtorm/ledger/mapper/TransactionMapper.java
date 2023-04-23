package org.maxtorm.ledger.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.bo.AccountTree;
import org.maxtorm.ledger.bo.Transaction;
import org.maxtorm.ledger.po.AccountPo;
import org.maxtorm.ledger.po.TransactionPo;

@Mapper(builder = @Builder(disableBuilder = true))
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction convert(TransactionPo transactionPo);

    TransactionPo convert(Transaction transaction);
}
