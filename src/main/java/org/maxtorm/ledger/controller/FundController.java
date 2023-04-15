package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fund")
@AllArgsConstructor
public class FundController {
    private static final Logger logger = LoggerFactory.getLogger(FundController.class);

}
