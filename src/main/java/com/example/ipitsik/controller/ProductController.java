package com.example.ipitsik.controller;

import com.example.ipitsik.config.ProductsConfiguration;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductsConfiguration productsConfiguration;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "currency") CurrencyEnum currency)
            throws ExchangeException, URISyntaxException {
        return ResponseEntity.ok(productService.finalizeProducts(productsConfiguration.getProducts(), currency));
    }
}
