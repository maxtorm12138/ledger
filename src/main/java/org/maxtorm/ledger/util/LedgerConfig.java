package org.maxtorm.ledger.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AccountInitializeProperties.class)
@AllArgsConstructor
@Getter
@Setter
public class LedgerConfig {
    private AccountInitializeProperties accountInitializeProperties;
}
