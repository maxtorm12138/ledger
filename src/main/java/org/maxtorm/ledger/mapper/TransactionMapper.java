package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.bo.Transaction;
import org.maxtorm.ledger.po.TransactionPo;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction convert(TransactionPo transactionPo);

    TransactionPo convert(Transaction transaction);
}
