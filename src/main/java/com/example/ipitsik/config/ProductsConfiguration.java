package com.example.ipitsik.config;

import com.example.ipitsik.entity.Product;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ComponentScan("com.example.ipitsik.service")
@ConfigurationProperties(prefix = "stock")
public class ProductsConfiguration {
    List<Product> products;
}
