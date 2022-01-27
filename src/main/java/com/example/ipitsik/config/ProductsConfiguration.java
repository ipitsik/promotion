package com.example.ipitsik.config;

import com.example.ipitsik.entity.Product;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "stock")
public class ProductsConfiguration {
    private List<Product> products;
    private String currency;
}
