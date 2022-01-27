package com.example.ipitsik.service.impl;

import com.example.ipitsik.controller.dto.CartItemDTO;
import com.example.ipitsik.controller.dto.ProductReceiptDTO;
import com.example.ipitsik.controller.dto.ReceiptDTO;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.ExchangeService;
import com.example.ipitsik.service.ReceiptGenerator;
import com.example.ipitsik.utils.Constants;
import com.example.ipitsik.utils.CurrencyEnum;
import com.example.ipitsik.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReceiptGeneratorImpl implements ReceiptGenerator {

    private final ExchangeService exchangeService;

    @Override
    public ReceiptDTO generateReceipt(ShoppingCart shoppingCart, CurrencyEnum fromCurrency, CurrencyEnum toCurrency) throws ExchangeException, URISyntaxException {

        double exchange = exchangeService.exchange(fromCurrency, toCurrency);

        List<CartItemDTO> cartItemDTOList = shoppingCart.getCartItems()
                .stream()
                .map(c -> new CartItemDTO(Utils.getCurrencySymbol(toCurrency) +
                        Constants.DF.format(c.getFinalPrice()* exchange),
                        c.getQuantity(),
                        new ProductReceiptDTO(c.getProduct().getId(), c.getProduct().getName(),
                                Utils.getCurrencySymbol(toCurrency) +
                                        Constants.DF.format(c.getProduct().getPrice() * exchange) )))
                .collect(Collectors.toList());

        return new ReceiptDTO(cartItemDTOList,
                Utils.getCurrencySymbol(toCurrency) +
                        Constants.DF.format(shoppingCart.getInitialPrice() * exchange),
                shoppingCart.getTotalDiscount() + Constants.PERCENTAGE,
                Utils.getCurrencySymbol(toCurrency) +
                        Constants.DF.format(shoppingCart.getFinalPrice() * exchange));
    }
}
