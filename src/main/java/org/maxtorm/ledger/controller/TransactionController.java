package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Api.TransferResponse transfer(@RequestBody Api.TransferRequest request) {
        var responseBuilder = Api.TransferResponse.newBuilder();

        return responseBuilder.build();
    }
}
