package org.maxtorm.ledger.controller;

import lombok.AllArgsConstructor;
import org.maxtorm.ledger.api.Api;
import org.maxtorm.ledger.service.AccountService;
import org.maxtorm.ledger.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/portal")
@CrossOrigin(origins = "*")
public class PortalController {
    private static final Logger logger = LoggerFactory.getLogger(PortalController.class);
    private AccountService accountService;

    @GetMapping("/home")
    public @ResponseBody Result<Api.GetHomeResponse> home() {
        var response = new Api.GetHomeResponse();

        response.setInitialized(true);
        return Result.success(response);
    }

}
