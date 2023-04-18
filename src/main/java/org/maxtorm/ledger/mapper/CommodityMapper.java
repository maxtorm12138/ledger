package org.maxtorm.ledger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.maxtorm.ledger.po.CommodityPo;
import org.maxtorm.ledger.proto.Entity;

@Mapper
public interface CommodityMapper {
    CommodityMapper INSTANCE = Mappers.getMapper(CommodityMapper.class);

    default CommodityPo ConvertFromString(String commodity) {
        return CommodityPo.of(commodity);
    }

    default String ConvertToString(CommodityPo commodity) {
        return commodity.toString();
    }

    Entity.Commodity Convert(CommodityPo commodityPo);

    default CommodityPo Convert(Entity.Commodity commodity) {
        return CommodityPo.of(String.join(".", commodity.getCategory(), commodity.getName(), commodity.getMarket()));
    }
}
