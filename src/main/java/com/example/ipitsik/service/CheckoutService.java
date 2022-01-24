package com.example.ipitsik.service;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.utils.CurrencyEnum;

import java.net.URISyntaxException;
import java.util.List;

public interface CheckoutService {

    ShoppingCart checkoutShoppingCart(List<Product> products, CurrencyEnum currency) throws URISyntaxException, ExchangeException;
}
