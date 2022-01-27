package com.example.ipitsik.service.impl;

import com.example.ipitsik.config.ProductsConfiguration;
import com.example.ipitsik.controller.dto.ProductDTO;
import com.example.ipitsik.controller.dto.ReceiptDTO;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.exception.PromotionException;
import com.example.ipitsik.service.CheckoutService;
import com.example.ipitsik.service.ProductService;
import com.example.ipitsik.service.ReceiptGenerator;
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

    private final ProductService productService;

    private final ReceiptGenerator receiptGenerator;

    private final ProductsConfiguration productsConfiguration;

    @Override
    public ReceiptDTO checkoutShoppingCartListProducts(List<ProductDTO> products, CurrencyEnum fromCurrency,
                                                       CurrencyEnum toCurrency) throws ExchangeException, URISyntaxException {

        return this.checkoutShoppingCart(productService.transformProducts(products), fromCurrency, toCurrency);

    }

    @Override
    public ReceiptDTO checkoutShoppingCartListItems(List<String> items, CurrencyEnum toCurrency) throws PromotionException, ExchangeException, URISyntaxException {

        return this.checkoutShoppingCart(productService.generateProductsFromItems(items),
                CurrencyEnum.valueOf(productsConfiguration.getCurrency()), toCurrency);

    }

    private ReceiptDTO checkoutShoppingCart(List<Product> products, CurrencyEnum fromCurrency, CurrencyEnum toCurrency) throws ExchangeException, URISyntaxException {

        ShoppingCart shoppingCart = generateShoppingCart(products);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(shoppingCart);
        kieSession.fireAllRules();
        kieSession.dispose();

        shoppingCartService.finalizeShoppingCart(shoppingCart);

        return receiptGenerator.generateReceipt(shoppingCart, fromCurrency, toCurrency);
    }

    private ShoppingCart generateShoppingCart(List<Product> products) {

        ShoppingCart shoppingCart = new ShoppingCart();

        products.forEach(p -> shoppingCartService.addProductToShoppingCart(shoppingCart, p));
        shoppingCartService.initializeShoppingCartPrice(shoppingCart);

        return shoppingCart;
    }
}
