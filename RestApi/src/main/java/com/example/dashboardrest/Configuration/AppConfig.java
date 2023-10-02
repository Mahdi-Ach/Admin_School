package com.example.dashboardrest.Configuration;

import com.example.dashboardrest.Utils.ExportToCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class AppConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
    @Bean
    public ExportToCsv getCsv(){
        return new ExportToCsv();
    }
}
