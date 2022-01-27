package com.example.ipitsik.service;

import com.example.ipitsik.controller.dto.ProductDTO;
import com.example.ipitsik.controller.dto.ReceiptDTO;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.utils.CurrencyEnum;

import java.net.URISyntaxException;
import java.util.List;

public interface CheckoutService {

    ReceiptDTO checkoutShoppingCartListProducts(List<ProductDTO> products, CurrencyEnum fromCurrency,
                                                CurrencyEnum toCurrency) throws ExchangeException, URISyntaxException;

    ReceiptDTO checkoutShoppingCartListItems(List<String> items, CurrencyEnum currency) throws PromotionException, ExchangeException, URISyntaxException;

}
