package com.example.ipitsik.controller;

import com.example.ipitsik.controller.model.ProductDTO;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.CheckoutService;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    private final ProductService productService;

    @PostMapping("/getReceipt/byProducts")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByProducts(@RequestBody List<ProductDTO> products,
                                                                       @RequestBody CurrencyEnum currencyEnum) {
        try {
            return new ResponseEntity(checkoutService.checkoutShoppingCart(productService.transformProducts(products),
                    currencyEnum),
                        HttpStatus.OK);
        } catch (URISyntaxException | ExchangeException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getReceipt/byItems")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByItems(@RequestBody List<String> items,
                                                                    @RequestBody CurrencyEnum currencyEnum) {
        return getShoppingCartResponseEntity(items, currencyEnum);
    }

    @GetMapping("/getReceipt/byItemsInRequest")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByItemsInRequest(@RequestParam(value = "items") List<String> items,
                                                                             @RequestParam(value = "currency") CurrencyEnum currency) {
        return getShoppingCartResponseEntity(items, currency);
    }

    private ResponseEntity<ShoppingCart> getShoppingCartResponseEntity(@RequestParam("items") List<String> items, @RequestParam("currency") CurrencyEnum currency) {
        try {
            return new ResponseEntity(checkoutService.checkoutShoppingCart(productService.generateProductsFromItems(items),
                    currency),
                    HttpStatus.OK);
        } catch (PromotionException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (URISyntaxException | ExchangeException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
