package org.maxtorm.ledger.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.maxtorm.ledger.entity.account.Account;
import org.maxtorm.ledger.entity.account.AccountMapper;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.commodity.CommodityPo;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.service.CommodityService;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {
    public static class Request {
        @Getter
        @Builder
        public static class CreateAccountRequest {
            @NotBlank
            private String parentGuid;
            @NotNull
            private Boolean placeholder;
            @NotBlank
            private String name;
            @NotNull
            private String description;
            @NotNull
            private String type;
            @NotNull
            private String icon;
            @NotBlank
            private String majorCommodityGuid;
        }
    }

    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface RequestMapper {
        @Mapping(target = "guid", ignore = true)
        @Mapping(target = "majorCommodity.guid", source = "majorCommodityGuid")
        Account convertFromOpenAccountRequest(Request.CreateAccountRequest createAccountRequest);
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;
    private CommodityService commodityService;
    private RequestMapper requestMapper;

    @PostMapping(value = "/create")
    @Transactional(Transactional.TxType.REQUIRED)
    public @ResponseBody Result<Void> create(@Valid @RequestBody Request.CreateAccountRequest request) {
        var majorCommodity = commodityService.getCommodity(request.getMajorCommodityGuid()).orElseThrow();
        var account = requestMapper.convertFromOpenAccountRequest(request);

        var accountToCreate = account.toBuilder();
        accountToCreate.majorCommodity(majorCommodity);

        if (account.getIcon().isBlank()) {
            accountToCreate.icon("none");
        }
        accountToCreate.guid(UUID.randomUUID().toString());


        accountService.create(accountToCreate.build());

        return Result.success();
    }
}
