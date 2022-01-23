package com.example.ipitsik.service;

import com.example.ipitsik.controller.model.ProductDTO;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.exception.PromotionException;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, Product> getProductHashMap();

    List<Product> generateProductsFromItems(List<String> items) throws PromotionException;

    List<Product> finalizeProducts(List<Product> products);

    List<Product> transformProducts(List<ProductDTO> products);

    void finalizeProduct(Product product);
}
