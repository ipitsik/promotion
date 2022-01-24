package com.example.ipitsik.service;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.utils.CurrencyEnum;

public interface ShoppingCartService {
    void addProductToShoppingCart(ShoppingCart shoppingCart, Product product);

    void initializeShoppingCartPrice(ShoppingCart shoppingCart);

    void finalizeShoppingCart(ShoppingCart shoppingCart, CurrencyEnum currency, double exchange);

    boolean containsProduct(ShoppingCart shoppingCart, Product product);
}
