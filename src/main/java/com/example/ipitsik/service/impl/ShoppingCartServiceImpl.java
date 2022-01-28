package com.example.ipitsik.service.impl;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.service.ShoppingCartService;
import com.example.ipitsik.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Override
    public void addProductToShoppingCart(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart.getCartItems() == null) {
            shoppingCart.setCartItems(new ArrayList<>());
        } else if (this.containsProduct(shoppingCart, product)) {
            shoppingCart.getCartItems()
                    .stream()
                    .filter(c -> c.getProduct().equals(product))
                    .findFirst()
                    .ifPresent(c -> c.setQuantity(c.getQuantity() + 1));
            return;
        }
        shoppingCart.getCartItems().add(new CartItem(product.getPrice(), 1, product));
    }

    @Override
    public void initializeShoppingCartPrice(ShoppingCart shoppingCart) {
        if (shoppingCart.getCartItems() != null) {
            shoppingCart.getCartItems().forEach(c -> shoppingCart.setInitialPrice(shoppingCart.getInitialPrice()
                    + c.getProduct().getPrice() * c.getQuantity()));
        }
    }

    @Override
    public void finalizeShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart.getCartItems() != null) {
            double finalCost = shoppingCart.getCartItems().stream().mapToDouble(c -> c.getFinalPrice() * c.getQuantity()).sum();

            if (shoppingCart.getTotalDiscount() > 0) {
                finalCost -= finalCost * shoppingCart.getTotalDiscount() * Constants.PERCENTAGE_MULTIPLE;
            }

            shoppingCart.setFinalPrice(finalCost);
        }
    }

    private boolean containsProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .anyMatch(product::equals);
    }
}
