package com.example.ipitsik.service.impl;

import com.example.ipitsik.config.ProductsConfiguration;
import com.example.ipitsik.controller.dto.ProductDTO;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsConfiguration productsConfiguration;

    private Map<String, Product> productHashMap;

    @PostConstruct
    public void init() {
        this.productHashMap = productsConfiguration
                .getProducts()
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    @Override
    public List<Product> generateProductsFromItems(List<String> items) throws PromotionException {

        this.validateItems(items);

        return items.stream()
                .map(i -> this.getProductHashMap().get(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> transformProducts(List<ProductDTO> products, CurrencyEnum fromCurrency) throws PromotionException {
        this.validateProducts(products, fromCurrency);
        List<Product> productList = new ArrayList<>();
        products.forEach(p -> productList.add(new Product(p.getId(), p.getName(), p.getPrice())));
        return productList;
    }

    private Map<String, Product> getProductHashMap(){
        return productHashMap;
    }

    private void validateItems(List<String> items) throws PromotionException {
        for (String item : items) {
            if (!this.getProductHashMap().containsKey(item)) {
                throw new PromotionException("There are products not in stock");
            }
        }
    }

    private void validateProducts(List<ProductDTO> products, CurrencyEnum currency) throws PromotionException {
        for(ProductDTO product:products){
            if(products.stream().filter(p -> p.getId().equals(product.getId()))
                    .anyMatch(p -> !p.getName().equals(product.getName()) || p.getPrice()!=product.getPrice())
                || (productHashMap.containsKey(product.getId())
                    && (!product.getName().equals(productHashMap.get(product.getId()).getName())
                        || product.getPrice() != productHashMap.get(product.getId()).getPrice()
                        || !currency.toString().equals(productsConfiguration.getCurrency())))){
                throw new PromotionException("Products are wrong");
            }
        }
    }
}
