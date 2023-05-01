package org.maxtorm.ledger.controller;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.service.CommodityService;
import org.maxtorm.ledger.util.Result;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/commodity")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CommodityController {

    public static class Request {
        @Getter
        public static class CreateCommodityRequest {
            @NotBlank
            private String fullName;
            @NotBlank
            private String mnemonic;
            @NotBlank
            private String namespace;
            @NotBlank
            private String quoteSource;
            @Positive
            private BigInteger fraction;
        }
    }

    public static class Response {
        @Getter
        @Builder
        public static class GetCommodityTreeResponse {
            private Map<String, Collection<Commodity>> commodities;
        }
    }

    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface RequestMapper {
        Commodity convertFromCreateCommodityRequest(Request.CreateCommodityRequest request);
    }

    private CommodityService commodityService;
    private RequestMapper requestMapper;

    @PostMapping("/create")
    public @ResponseBody Result<Void> create(@Valid @RequestBody Request.CreateCommodityRequest request) {
        var commodity = requestMapper.convertFromCreateCommodityRequest(request).toBuilder().guid(UUID.randomUUID().toString()).build();
        commodityService.create(commodity);
        return Result.success();
    }

    @GetMapping("/tree")
    public @ResponseBody Result<Response.GetCommodityTreeResponse> tree() {
        var commoityList = commodityService.getAll();
        HashMultimap<String, Commodity> commodities = HashMultimap.create();
        commoityList.forEach(commodity -> commodities.put(commodity.getNamespace(), commodity));

        var responseBuilder = Response.GetCommodityTreeResponse.builder();
        responseBuilder.commodities(commodities.asMap());

        return Result.success(responseBuilder.build());
    }
}
