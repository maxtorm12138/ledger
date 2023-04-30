package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.entity.commodity.Currency;
import org.maxtorm.ledger.entity.commodity.Fund;
import org.maxtorm.ledger.entity.commodity.exception.CommodityProviderNetworkFailException;
import org.maxtorm.ledger.entity.commodity.providers.IFundProvider;
import org.maxtorm.ledger.util.ErrorCode;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fund")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FundController {
    private static final Logger logger = LoggerFactory.getLogger(FundController.class);
    private IFundProvider fundProvider;

    @GetMapping("/nav")
    public @ResponseBody Result<Api.GetFundNavResponse> fundNav(@RequestParam String code) {
        try {
            var response = new Api.GetFundNavResponse();
            Fund fund = new Fund("fund.%s".formatted(code));
            var nav = fundProvider.getFundNav(fund);
            response.setFund(fund);
            response.setDate(nav.getFirst());
            response.setNav(nav.getSecond());
            // FIXME
            response.setCurrency(Currency.CNY);
            return Result.success(response);
        } catch (CommodityProviderNetworkFailException e) {
            return Result.fail(ErrorCode.LogicError, e.getMessage());
        }
    }
}
