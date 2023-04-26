package org.maxtorm.ledger.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.TransactionPo;
import org.maxtorm.ledger.transaction.Transaction;

@Mapper(builder = @Builder(disableBuilder = true))
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction convert(TransactionPo transactionPo);

    TransactionPo convert(Transaction transaction);
}
