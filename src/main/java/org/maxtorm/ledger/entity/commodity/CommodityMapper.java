package org.maxtorm.ledger.entity.commodity;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommodityMapper {
    Commodity convertPoToBo(CommodityPo commodityPo);

    CommodityPo convertBoToPo(Commodity commodity);

    List<Commodity> convertListPoToBo(List<CommodityPo> commodityPoList);
}
