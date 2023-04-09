package org.maxtorm.ledger.controller;

import org.maxtorm.ledger.LedgerAPIResponse;
import org.maxtorm.ledger.api.record.BasicFundInfo;
import org.maxtorm.ledger.error.ErrorCode;
import org.maxtorm.ledger.service.FundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fund")
public class FundController {

    private static final Logger logger = LoggerFactory.getLogger(FundController.class);

    @Autowired
    private FundService fundService;

    @RequestMapping("/subscribe")
    LedgerAPIResponse<Void> subscribe(@RequestParam String code) {
        BasicFundInfo fundInfo = fundService.getBasicFundInfo(code);


        return new LedgerAPIResponse<>(ErrorCode.Success);
    }
}
