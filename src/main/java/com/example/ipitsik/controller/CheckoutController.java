package com.example.ipitsik.controller;

import com.example.ipitsik.controller.dto.ProductDTO;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.CheckoutService;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout/receipt")
public class CheckoutController {

    private final CheckoutService checkoutService;

    private final ProductService productService;

    @PostMapping("/byProducts")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByProducts(@RequestBody List<ProductDTO> products,
                                                                       @RequestBody CurrencyEnum currency)
            throws ExchangeException, URISyntaxException {
        return ResponseEntity.ok(checkoutService.checkoutShoppingCart(productService.transformProducts(products), currency));
    }

    @PostMapping("/byItems")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByItems(@RequestBody List<String> items,
                                                                    @RequestBody CurrencyEnum currency)
            throws PromotionException, ExchangeException, URISyntaxException {
        return ResponseEntity.ok(checkoutService.checkoutShoppingCart(productService.generateProductsFromItems(items), currency));
    }

    @GetMapping("/byItemsInRequest")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByItemsInRequest(@RequestParam(value = "items") List<String> items,
                                                                             @RequestParam(value = "currency") CurrencyEnum currency)
            throws PromotionException, ExchangeException, URISyntaxException {
        return ResponseEntity.ok(checkoutService.checkoutShoppingCart(productService.generateProductsFromItems(items), currency));
    }
}
