package com.rbc.shopping.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Anmoldeep Singh Kang
 * This class ensures that recommendation can only be called by 'shopping.rbc.com'
 */
@Configuration
@EnableWebSecurity
public class CorsConfig {

	/**
	 * @return WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/recommendations/**")
				        .allowedOrigins("https://shopping.rbc.com");
			}
		};
	}
}
