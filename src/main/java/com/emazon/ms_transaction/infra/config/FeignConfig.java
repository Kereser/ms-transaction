package com.emazon.ms_transaction.infra.config;

import com.emazon.ms_transaction.ConsUtils;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header(ConsUtils.AUTHORIZATION_HEADER, getTokenFromAuth());
    }

    private String getTokenFromAuth() {
        return ConsUtils.BEARER + SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
