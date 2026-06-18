package com.doconnectai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



/**
 * Configures Cross-Origin Resource Sharing (CORS) for the application.
 *
 * Allows the React frontend running on localhost:3000 to access
 * Spring Boot REST APIs and enables credential sharing such as
 * Authorization headers and cookies.
 */


@Configuration
public class CorsConfig {

	 @Bean
	    public CorsFilter corsFilter() {

	        CorsConfiguration config = new CorsConfiguration();

	        config.addAllowedOrigin("http://localhost:3000");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);

	        return new CorsFilter(source);
	
	 }
	 
}

