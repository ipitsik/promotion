package com.example.ipitsik.controller;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/shoppingCart")
    public ShoppingCart calculateShoppingCart(@RequestBody List<Product> products) {
        return checkoutService.getShoppingCart(products);
    }
}
