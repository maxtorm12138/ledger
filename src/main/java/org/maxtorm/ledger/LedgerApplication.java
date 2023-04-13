package org.maxtorm.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@SpringBootApplication
public class LedgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LedgerApplication.class, args);
    }

    @Bean
    public DataSource dataSource(LedgerConfiguration ledgerConfiguration) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(ledgerConfiguration.getDatasource().getDriverClassName());
        dataSource.setUrl(ledgerConfiguration.getDatasource().getUrl());

        return dataSource;
    }
}
