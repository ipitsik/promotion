package com.example.ipitsik.service.impl;

import com.example.ipitsik.config.ProductsConfiguration;
import com.example.ipitsik.controller.model.ProductDTO;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsConfiguration productsConfiguration;

    private static Map<String, Product> productHashMap = null;

    @Override
    public Map<String, Product> getProductHashMap(){
        if (productHashMap == null || productHashMap.isEmpty()){
            productHashMap = productsConfiguration
                    .getProducts()
                    .stream()
                    .collect(Collectors.toMap(Product::getId, Function.identity()));
        }
        return productHashMap;
    }

    @Override
    public List<Product> generateProductsFromItems(List<String> items) throws PromotionException {

        this.validateProducts(items);

        return items.stream()
                .map(i -> this.getProductHashMap().get(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> finalizeProducts(List<Product> products) {
        products.forEach(this::finalizeProduct);
        return products;
    }

    @Override
    public List<Product> transformProducts(List<ProductDTO> products) {
        List<Product> productList = new ArrayList<>();
        products.forEach(p -> productList.add(new Product(p.getId(), p.getName(), p.getPrice(), null)));
        return productList;
    }

    @Override
    public void finalizeProduct(Product product) {
        product.setPriceReceipt(Constants.BRITISH_POUND + Constants.DF.format(product.getPrice()));
    }

    private void validateProducts(List<String> items) throws PromotionException {
        for(String item : items){
            if(!this.getProductHashMap().containsKey(item)){
                throw new PromotionException("There are products not in stock");
            }
        }
    }
}
