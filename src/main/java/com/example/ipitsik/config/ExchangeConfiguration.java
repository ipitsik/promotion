package com.example.ipitsik.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "exchange")
public class ExchangeConfiguration {
    private String converterUrl;
    private boolean enabled;
}
