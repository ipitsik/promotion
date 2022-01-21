package com.example.ipitsik.service.impl;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final KieContainer kieContainer;

    @Override
    public ShoppingCart getShoppingCart(List<Product> products) {

        ShoppingCart shoppingCart = generateShoppingCart(products);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(shoppingCart);
        kieSession.fireAllRules();
        kieSession.dispose();

        shoppingCart.calculateFinalCost();

        return shoppingCart;
    }

    private ShoppingCart generateShoppingCart(List<Product> products){

        ShoppingCart shoppingCart = new ShoppingCart();

        products.forEach(shoppingCart::addProductToShoppingCart);
        shoppingCart.calculateInitialCost();

        return shoppingCart;
    }
}
