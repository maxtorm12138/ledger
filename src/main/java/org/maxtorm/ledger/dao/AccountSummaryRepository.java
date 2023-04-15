package org.maxtorm.ledger.dao;

import org.maxtorm.ledger.po.AccountSummaryPo;
import org.springframework.data.repository.CrudRepository;

public interface AccountSummaryRepository extends CrudRepository<AccountSummaryPo, String> {
}
