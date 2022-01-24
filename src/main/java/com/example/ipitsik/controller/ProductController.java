package com.example.ipitsik.controller;

import com.example.ipitsik.config.ProductsConfiguration;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final ProductsConfiguration productsConfiguration;

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "currency") CurrencyEnum currency) {
        try {
            return new ResponseEntity(productService.finalizeProducts(productsConfiguration.getProducts(),
                    currency), HttpStatus.OK);
        } catch (ExchangeException | URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
