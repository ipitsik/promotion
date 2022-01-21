package com.example.ipitsik.service;

import com.example.ipitsik.config.ApplicationConfiguration;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.service.impl.CheckoutServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class CheckoutServiceImplTest {

    @Autowired
    private CheckoutServiceImpl checkoutService;

    private final Product p1 = new Product();
    private final Product p2 = new Product();
    private final Product p3 = new Product();

    {
        p1.setId("001");
        p1.setName("Water Bottle");
        p1.setPrice(24.95);

        p2.setId("002");
        p2.setName("Hoodie");
        p2.setPrice(65.0);

        p3.setId("003");
        p3.setName("Sticker Set");
        p3.setPrice(3.99);
    }

    @Test
    public void testCase1() {

        // arrange
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p1);
        products.add(p2);
        products.add(p3);

        // act
        ShoppingCart shoppingCart = checkoutService.getShoppingCart(products);

        // assert
        String expectedPrice = "£103.47";
        Assert.assertEquals(expectedPrice, shoppingCart.getFinalCost());
    }

    @Test
    public void testCase2() {

        // arrange
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p1);
        products.add(p1);

        // act
        ShoppingCart shoppingCart = checkoutService.getShoppingCart(products);

        // assert
        String expectedPrice = "£68.97";
        Assert.assertEquals(expectedPrice, shoppingCart.getFinalCost());
    }

    @Test
    public void testCase3() {

        // arrange
        List<Product> products = new ArrayList<>();
        products.add(p2);
        products.add(p2);
        products.add(p3);

        // act
        ShoppingCart shoppingCart = checkoutService.getShoppingCart(products);

        // assert
        String expectedPrice = "£120.59";
        Assert.assertEquals(expectedPrice, shoppingCart.getFinalCost());
    }
}
