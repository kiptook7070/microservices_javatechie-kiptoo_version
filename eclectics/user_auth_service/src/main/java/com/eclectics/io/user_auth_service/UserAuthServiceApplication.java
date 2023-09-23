package com.eclectics.io.user_auth_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class UserAuthServiceApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserAuthServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
           LOGGER.info("USER AUTHENTICATION SERVICE INITIALIZED SUCCESSFULLY AT : " + new Date());
        };
    }
}
