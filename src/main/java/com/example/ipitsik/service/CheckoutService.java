package com.example.ipitsik.service;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;

import java.util.List;

public interface CheckoutService {

    ShoppingCart checkoutShoppingCart(List<Product> products);
}
