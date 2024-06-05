package com.example.pv207api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class pv207apiApplication {

    public static void main(String[] args) {
        SpringApplication.run(pv207apiApplication.class, args);
    }
}
