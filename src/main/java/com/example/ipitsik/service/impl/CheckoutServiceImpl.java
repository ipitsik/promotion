package com.example.ipitsik.service.impl;

import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.CheckoutService;
import com.example.ipitsik.service.ExchangeService;
import com.example.ipitsik.service.ShoppingCartService;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final KieContainer kieContainer;

    private final ShoppingCartService shoppingCartService;

    private final ExchangeService exchangeService;

    @Override
    public ShoppingCart checkoutShoppingCart(List<Product> products, CurrencyEnum currency) throws URISyntaxException, ExchangeException {

        ShoppingCart shoppingCart = generateShoppingCart(products);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(shoppingCart);
        kieSession.fireAllRules();
        kieSession.dispose();

        shoppingCartService.finalizeShoppingCart(shoppingCart, currency,
                exchangeService.exchange(currency));

        return shoppingCart;
    }

    private ShoppingCart generateShoppingCart(List<Product> products) {

        ShoppingCart shoppingCart = new ShoppingCart();

        products.forEach(p -> shoppingCartService.addProductToShoppingCart(shoppingCart, p));
        shoppingCartService.initializeShoppingCartPrice(shoppingCart);

        return shoppingCart;
    }
}
