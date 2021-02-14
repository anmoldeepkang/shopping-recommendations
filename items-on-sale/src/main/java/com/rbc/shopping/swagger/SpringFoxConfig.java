package com.rbc.shopping.swagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Anmoldeep Singh Kang
 * This class contains the configuration for Swagger documentation for API.
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    /**
     * @return Docket
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rbc.shopping.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo()).securitySchemes(basicScheme());
    }
    private List<SecurityScheme> basicScheme() {
        List<SecurityScheme> schemeList = new ArrayList<>();
        schemeList.add(new BasicAuth("basicAuth"));
        return schemeList;
    }
    /**
     * @return ApiInfo
     */
    public ApiInfo getApiInfo() {
        return new ApiInfo(
                "Items on Sale Microservice",
                "This is a Spring boot application to suggest recommendations for items on sale.",
                "V1",
                "urn:tos",
                new springfox.documentation.service.Contact("Anmoldeep Singh Kang", null, "anmol_kang1@yahoo.co.in"),
                "CC BY-SA 3.0",
                null,
                Collections.emptyList()
        );
    }
}
