package com.greenbridge.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.*;



/**
 * @author VincentMilione
 * ThymeleafConfiguration
 */
@Configuration
public class ThymeleafConfiguration {
    
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }    
}