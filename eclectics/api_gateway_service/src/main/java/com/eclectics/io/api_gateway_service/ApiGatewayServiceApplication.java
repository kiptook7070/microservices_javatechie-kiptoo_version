package com.eclectics.io.api_gateway_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ApiGatewayServiceApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lineRunner() {
        return args -> {
            LOGGER.info("API GATEWAY SERVICE INITIALIZED SUCCESSFULLY AT : " + new Date());
        };
    }

}
