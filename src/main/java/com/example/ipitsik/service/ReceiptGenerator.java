package com.example.ipitsik.service;

import com.example.ipitsik.controller.dto.ReceiptDTO;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.utils.CurrencyEnum;

import java.net.URISyntaxException;

public interface ReceiptGenerator {
    ReceiptDTO generateReceipt(ShoppingCart shoppingCart, CurrencyEnum currency) throws ExchangeException, URISyntaxException;
}
