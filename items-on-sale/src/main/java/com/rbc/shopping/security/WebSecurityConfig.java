package com.rbc.shopping.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
 
/**
 * @author Anmoldeep Singh Kang
 * This class implements the Security Configuration for the application.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//The swagger documentation related endpoints are not authenticated.
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated().and()
				.httpBasic().authenticationEntryPoint(authenticationDeniedHandler());
	}

	@Bean
	public FilterRegistrationBean<RecommendationRequestFilter> loggingFilter() {
		FilterRegistrationBean<RecommendationRequestFilter> registrationBean 
          = new FilterRegistrationBean<>();
            
        registrationBean.setFilter(new RecommendationRequestFilter());
        registrationBean.addUrlPatterns("/recommendations/*");
            
        return registrationBean;    
    }	
    @Bean
    public AuthenticationEntryPoint authenticationDeniedHandler(){
        return new RestAuthenticationEntryPoint();
    }
    
 
}
