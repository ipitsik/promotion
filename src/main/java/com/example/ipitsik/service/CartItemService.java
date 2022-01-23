package com.example.ipitsik.service;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;

public interface CartItemService {
    void increaseQuantity(CartItem cartItem);

    void finalizeItem(CartItem cartItem);

    CartItem initializeCartItemWithProduct(Product product);
}
