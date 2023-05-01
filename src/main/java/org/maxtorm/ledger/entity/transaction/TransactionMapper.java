//package org.maxtorm.ledger.entity.transaction;
//
//import org.mapstruct.Builder;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(builder = @Builder(disableBuilder = true))
//public interface TransactionMapper {
//    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
//
//    default TransactionPo convert(Transaction transaction) {
//        if (transaction instanceof TransactionFund) {
//            return convertFundBoToPo((TransactionFund) transaction);
//        }
//
//        return convertBoToPo(transaction);
//    }
//
//    @Mapping(target = "fund", ignore = true)
//    @Mapping(target = "stock", ignore = true)
//    @Mapping(target = "createTime", ignore = true)
//    @Mapping(target = "updateTime", ignore = true)
//    TransactionPo convertBoToPo(Transaction transaction);
//
//    @Mapping(target = "referenceNumber", source = "referenceNumber")
//    @Mapping(target = "fund.referenceNumber", source = "referenceNumber")
//    @Mapping(target = "fund.nav", source = "nav")
//    @Mapping(target = "fund.averageCost", source = "averageCost")
//    @Mapping(target = "fund.closed", source = "closed")
//    @Mapping(target = "stock", ignore = true)
//    @Mapping(target = "createTime", ignore = true)
//    @Mapping(target = "updateTime", ignore = true)
//    TransactionPo convertFundBoToPo(TransactionFund transactionFund);
//}
