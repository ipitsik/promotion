package com.example.ipitsik.service;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.utils.CurrencyEnum;

public interface CartItemService {
    void increaseQuantity(CartItem cartItem);

    void finalizeItem(CartItem cartItem, CurrencyEnum currency, double exchange);

    CartItem initializeCartItemWithProduct(Product product);
}
