package com.example.ipitsik.controller;

import com.example.ipitsik.controller.model.ProductDTO;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.CheckoutService;
import com.example.ipitsik.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    private final ProductService productService;

    @PostMapping("/getReceipt/byProducts")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByProducts(@RequestBody List<ProductDTO> products) {
        return new ResponseEntity(checkoutService.checkoutShoppingCart(productService.transformProducts(products)),
                    HttpStatus.OK);
    }

    @PostMapping("/getReceipt/byItems")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByItems(@RequestBody List<String> items) {
        try {
            return new ResponseEntity(checkoutService.checkoutShoppingCart(productService.generateProductsFromItems(items)),
                    HttpStatus.OK);
        } catch (PromotionException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getReceipt/byItemsInRequest")
    public ResponseEntity<ShoppingCart> checkoutShoppingCartByItemsInRequest(@RequestParam(value = "items") List<String> items) {
        try {
            return new ResponseEntity(checkoutService.checkoutShoppingCart(productService.generateProductsFromItems(items)),
                    HttpStatus.OK);
        } catch (PromotionException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
