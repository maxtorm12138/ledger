package org.maxtorm.ledger.entity.commodity;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface CommodityRepository extends JpaRepository<CommodityPo, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CommodityPo> getCommodityPoByNamespaceAndMnemonic(String namespace, String mnemonic);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CommodityPo> getCommodityPoByGuid(String guid);
}
