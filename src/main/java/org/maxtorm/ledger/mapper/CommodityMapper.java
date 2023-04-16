package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.CommodityPo;

@Mapper
public interface CommodityMapper {
    CommodityMapper INSTANCE = Mappers.getMapper(CommodityMapper.class);

    default CommodityPo Convert(String commodity) {
        return CommodityPo.of(commodity);
    }

    default String Convert(CommodityPo commodity) {
        return commodity.toString();
    }

}
