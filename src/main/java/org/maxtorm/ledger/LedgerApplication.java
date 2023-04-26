package org.maxtorm.ledger;

import org.maxtorm.ledger.entity.commodity.providers.EasyMoneyProvider;
import org.maxtorm.ledger.entity.commodity.providers.IFundProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zalando.logbook.Logbook;

@SpringBootApplication
public class LedgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LedgerApplication.class, args);
    }

    @Bean
    Logbook logbook() {
        return Logbook.create();
    }

    @Bean
    IFundProvider fundProvider() {
        return new EasyMoneyProvider();
    }
}
