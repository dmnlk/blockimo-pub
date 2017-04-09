package com.dmnlk.blockimo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Environment Configuration
 */
@Component
@ConfigurationProperties(prefix = "settings")
@Data
public class EnvironmentConfig {
    private String baseUrl;
    private boolean enableBlock;
}
