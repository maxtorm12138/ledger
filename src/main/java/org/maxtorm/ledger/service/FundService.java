package org.maxtorm.ledger.service;


import org.maxtorm.ledger.LedgerConfiguration;
import org.maxtorm.ledger.api.APIError;
import org.maxtorm.ledger.api.FundAPI;
import org.maxtorm.ledger.api.record.BasicFundInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FundService {
    private static final Logger logger = LoggerFactory.getLogger(FundService.class);

    private LedgerConfiguration ledgerConfiguration;

    public BasicFundInfo getBasicFundInfo(String code) {
        FundAPI api = new FundAPI(ledgerConfiguration.getApiToken());
        return api.getBasicInfo(code);
    }
}
