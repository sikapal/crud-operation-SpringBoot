package com.skpcorp.crudOperation.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@Configuration
@EntityScan("com.skpcorp.crudOperation.domain")
@EnableJpaRepositories("com.skpcorp.crudOperation.repos")
@EnableTransactionManagement
public class DomainConfig {
	@Bean
    public SpringDataDialect springDataDialect() {
       return new SpringDataDialect();
    }

}
