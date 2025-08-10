package com.quicken.ordersms.configs;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

//    @Bean
//    public Flyway flyway() {
//        Flyway flyway = Flyway.configure()
//                .dataSource(dbUrl, dbUsername, dbPassword)
//                .locations("classpath:db/migration")
//                .baselineOnMigrate(true)
//                .load();
//
//        flyway.migrate();
//        return flyway;
//    }
}
