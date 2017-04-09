package com.dmnlk.blockimo.config;

import com.dmnlk.blockimo.interceptor.SampleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * configuration for app
 */
@Configuration
public class WebApplicationConfiguration {
    @Bean
    public SampleInterceptor sampleInterceptor() {
        return new SampleInterceptor();
    }

    @Bean
    public MappedInterceptor interceptor() {
        return new MappedInterceptor(new String[]{"/**"}, sampleInterceptor());
    }
}
