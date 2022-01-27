package com.example.ipitsik.service;

import com.example.ipitsik.controller.dto.ProductDTO;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.exception.PromotionException;

import java.util.List;

public interface ProductService {

    List<Product> generateProductsFromItems(List<String> items) throws PromotionException;

    List<Product> transformProducts(List<ProductDTO> products);
}
