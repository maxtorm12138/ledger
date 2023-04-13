package org.maxtorm.ledger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "datasource")
class LedgerDataSourceConfiguration {
  private String url;
  private String driverClassName;
}

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ledger")
public class LedgerConfiguration {
  private String apiToken;
  private LedgerDataSourceConfiguration datasource;
}
