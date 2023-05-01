package org.maxtorm.ledger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.commodity.CommodityMapper;
import org.maxtorm.ledger.entity.commodity.CommodityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommodityService {
    private static final Logger logger = LoggerFactory.getLogger(CommodityService.class);

    private CommodityMapper commodityMapper;
    private CommodityRepository commodityRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Commodity> getCommodity(String namespace, String mnemonic) {
        return commodityRepository.getCommodityPoByNamespaceAndMnemonic(namespace, mnemonic).map(commodityPo -> commodityMapper.convertPoToBo(commodityPo));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Commodity> getCommodity(String guid) {
        return commodityRepository.getCommodityPoByGuid(guid).map(commodityPo -> commodityMapper.convertPoToBo(commodityPo));
    }


    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Commodity commodity) {
        var commodityPo = commodityMapper.convertBoToPo(commodity);
        commodityRepository.saveAndFlush(commodityPo);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public List<Commodity> getAll() {
        return commodityMapper.convertListPoToBo(commodityRepository.findAll());
    }
}
