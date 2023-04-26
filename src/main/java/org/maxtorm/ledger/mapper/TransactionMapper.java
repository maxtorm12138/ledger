package org.maxtorm.ledger.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.TransactionFundPo;
import org.maxtorm.ledger.po.TransactionPo;
import org.maxtorm.ledger.po.TransactionStockPo;
import org.maxtorm.ledger.transaction.Transaction;
import org.maxtorm.ledger.transaction.TransactionFund;
import org.maxtorm.ledger.transaction.TransactionStock;

@Mapper(builder = @Builder(disableBuilder = true))
public interface TransactionMapper {
}
