package com.realhansookim.chimhaha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public OpenAPI chimhahaOpenAPI(){
        Info info = new Info().version("0.0.1").title("침하하API").description("침하하 Restful API명세서");
        return new OpenAPI().info(info);
    }  
}
