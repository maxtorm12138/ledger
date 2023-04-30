package org.maxtorm.ledger.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.maxtorm.ledger.entity.account.Account;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;

@Getter
@Setter
@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:account.yml", factory = YamlPropertySourceFactory.class)
public class AccountInitializeProperties {
    private HashMap<String, AccountInitialize> accountsToInitialize;
    private Commodity majorCommodity;

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountInitialize extends Account {
        private HashMap<String, AccountInitialize> children;
    }
}
