package com.example.ipitsik.service.impl;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.service.CartItemService;
import com.example.ipitsik.service.ShoppingCartService;
import com.example.ipitsik.utils.Constants;
import com.example.ipitsik.utils.CurrencyEnum;
import com.example.ipitsik.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartItemService cartItemService;

    @Override
    public void addProductToShoppingCart(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart.getCartItems() == null) {
            shoppingCart.setCartItems(new ArrayList<>());
        } else if (this.containsProduct(shoppingCart, product)) {
            shoppingCart.getCartItems()
                .stream()
                .filter(c -> c.getProduct().equals(product))
                .findFirst()
                .ifPresent(cartItemService::increaseQuantity);
            return;
        }
        shoppingCart.getCartItems().add(cartItemService.initializeCartItemWithProduct(product));
    }

    @Override
    public void initializeShoppingCartPrice(ShoppingCart shoppingCart){
        shoppingCart.getCartItems().forEach(c -> shoppingCart.setInitialPrice(shoppingCart.getInitialPrice()
                + c.getProduct().getPrice() * c.getQuantity()));
    }

    @Override
    public void finalizeShoppingCart(ShoppingCart shoppingCart, CurrencyEnum currency, double exchange){
        double finalCost = shoppingCart.getCartItems().stream().mapToDouble(c -> c.getFinalPrice() * c.getQuantity()).sum();
        shoppingCart.getCartItems().forEach(c -> cartItemService.finalizeItem(c, currency, exchange));

        if(shoppingCart.getTotalDiscount() > 0){
            finalCost -= finalCost * shoppingCart.getTotalDiscount() * Constants.PERCENTAGE_MULTIPLE;
        }

        shoppingCart.setInitialPriceInReceipt(Utils.getCurrencySymbol(currency) +
                Constants.DF.format(shoppingCart.getInitialPrice() * exchange));
        shoppingCart.setTotalDiscountInReceipt(shoppingCart.getTotalDiscount() + Constants.PERCENTAGE);
        shoppingCart.setFinalPriceInReceipt(Utils.getCurrencySymbol(currency) +
                Constants.DF.format(finalCost * exchange));
    }

    @Override
    public boolean containsProduct(ShoppingCart shoppingCart, Product product){
        return shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .anyMatch(product::equals);
    }
}
