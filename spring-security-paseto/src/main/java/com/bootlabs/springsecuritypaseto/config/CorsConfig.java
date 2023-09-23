package com.bootlabs.springsecuritypaseto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(false)
                .allowedMethods("GET", "HEAD", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "TRACE")
                .maxAge(3600)
                .allowedHeaders("*");
    }
}