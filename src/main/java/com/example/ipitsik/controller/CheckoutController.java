package com.example.ipitsik.controller;

import com.example.ipitsik.controller.dto.ProductDTO;
import com.example.ipitsik.controller.dto.ReceiptDTO;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.CheckoutService;
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

    @PostMapping("/byProducts")
    public ResponseEntity<ReceiptDTO> checkoutShoppingCartByProducts(@RequestBody List<ProductDTO> products,
                                                                     @RequestParam(value = "fromCurrency") CurrencyEnum fromCurrency,
                                                                     @RequestParam(value = "toCurrency") CurrencyEnum toCurrency)
            throws ExchangeException, URISyntaxException, PromotionException {
        return ResponseEntity.ok(checkoutService.checkoutShoppingCartListProducts(products, fromCurrency, toCurrency));
    }

    @PostMapping("/byItems")
    public ResponseEntity<ReceiptDTO> checkoutShoppingCartByItems(@RequestBody List<String> items,
                                                                  @RequestParam(value = "toCurrency") CurrencyEnum toCurrency)
            throws PromotionException, ExchangeException, URISyntaxException {
        return ResponseEntity.ok(checkoutService.checkoutShoppingCartListItems(items, toCurrency));
    }

    @GetMapping("/byItemsInRequest")
    public ResponseEntity<ReceiptDTO> checkoutShoppingCartByItemsInRequest(@RequestParam(value = "items") List<String> items,
                                                                           @RequestParam(value = "toCurrency") CurrencyEnum toCurrency)
            throws PromotionException, ExchangeException, URISyntaxException {
        return ResponseEntity.ok(checkoutService.checkoutShoppingCartListItems(items, toCurrency));
    }
}
