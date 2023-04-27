package org.maxtorm.ledger.util;

import lombok.Getter;
import lombok.Setter;
import org.maxtorm.ledger.entity.account.Account;
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
    private HashMap<String, Account> accounts;
}
