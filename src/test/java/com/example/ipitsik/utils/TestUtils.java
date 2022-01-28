package com.example.ipitsik.utils;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TestUtils {

    public static final Product product1 = new Product("001", "test product1", 10);

    public static final Product product2 = new Product("002", "test product2", 15);

    public static final Product product3 = new Product("003", "test product3", 20);

    public static ShoppingCart generateShoppingCart_threeProduct1_discount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(10, 3, TestUtils.product1));
        shoppingCart.setCartItems(cartItemList);
        shoppingCart.setTotalDiscount(10);
        return shoppingCart;
    }

    public static ShoppingCart generateShoppingCart_threeProduct1() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(10, 3, TestUtils.product1));
        shoppingCart.setCartItems(cartItemList);
        shoppingCart.setInitialPrice(30);
        shoppingCart.setFinalPrice(30);
        return shoppingCart;
    }

    public static ShoppingCart generateShoppingCart_twoProduct1_oneProduct2() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(10, 2, TestUtils.product1));
        cartItemList.add(new CartItem(15, 1, TestUtils.product2));
        shoppingCart.setCartItems(cartItemList);
        return shoppingCart;
    }

    public static LinkedHashMap<String, Double> generateReturnFromRestTemplate() {
        LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("EUR_USD", 1.2);
        return linkedHashMap;
    }

}
