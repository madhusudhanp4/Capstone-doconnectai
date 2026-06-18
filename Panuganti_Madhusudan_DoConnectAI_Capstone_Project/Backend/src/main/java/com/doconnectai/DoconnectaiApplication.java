package com.doconnectai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * Project      : DocConnectAI
 * Module       : Application Bootstrap
 * File Name    : DoconnectaiApplication.java
 * Author       : Panuganti Madhusudan
 * Created Date : 12 June 2026
 * Description  : Entry point of the Spring Boot application.
 *                Initializes the application context and starts the embedded server.
 *
 * Design Patterns Used:
 * - Singleton Pattern: Spring Boot manages beans as singletons by default
 */


@SpringBootApplication
public class DoconnectaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoconnectaiApplication.class, args);
    }

    /**
     * Creates a RestTemplate bean for making HTTP calls to external services
     * such as AI microservice integration.
     *
     * @return RestTemplate instance
     */
    
    
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}