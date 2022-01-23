package com.example.ipitsik.service.impl;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.service.CartItemService;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final ProductService productService;

    @Override
    public void increaseQuantity(CartItem cartItem){
        cartItem.setQuantity(cartItem.getQuantity() + 1);
    }

    @Override
    public void finalizeItem(CartItem cartItem) {
        cartItem.setFinalPriceReceipt(Constants.BRITISH_POUND + Constants.DF.format(cartItem.getFinalPrice()));
        productService.finalizeProduct(cartItem.getProduct());
    }

    @Override
    public CartItem initializeCartItemWithProduct(Product product){
        return new CartItem(product.getPrice(), null, 1, product);
    }
}
