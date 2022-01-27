package com.example.ipitsik.service;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;

public interface ShoppingCartService {
    void addProductToShoppingCart(ShoppingCart shoppingCart, Product product);

    void initializeShoppingCartPrice(ShoppingCart shoppingCart);

    void finalizeShoppingCart(ShoppingCart shoppingCart);
}
