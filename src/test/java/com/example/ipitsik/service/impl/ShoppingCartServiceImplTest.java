package com.example.ipitsik.service.impl;

import com.example.ipitsik.entity.CartItem;
import com.example.ipitsik.entity.Product;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Test
    void addProductToShoppingCart_product1() {
        // arrange
        ShoppingCart shoppingCart = new ShoppingCart();

        // act
        shoppingCartService.addProductToShoppingCart(shoppingCart, TestUtils.product1);

        // assert
        Assert.assertNotNull(shoppingCart.getCartItems());
        Assert.assertEquals(1, shoppingCart.getCartItems().size());

        Product productResult = shoppingCart.getCartItems().get(0).getProduct();
        Assertions.assertEquals(10, productResult.getPrice());
        Assert.assertEquals("001", productResult.getId());
        Assert.assertEquals("test product1", productResult.getName());
    }

    @Test
    void addProductToShoppingCart_product1_product1() {
        // arrange
        ShoppingCart shoppingCart = new ShoppingCart();

        // act
        shoppingCartService.addProductToShoppingCart(shoppingCart, TestUtils.product1);
        shoppingCartService.addProductToShoppingCart(shoppingCart, TestUtils.product1);

        // assert
        Assert.assertNotNull(shoppingCart.getCartItems());
        Assert.assertEquals(1, shoppingCart.getCartItems().size());

        CartItem cartItem = shoppingCart.getCartItems().get(0);
        Assert.assertEquals(2, cartItem.getQuantity());
    }

    @Test
    void addProductToShoppingCart_product1_product2() {
        // arrange
        ShoppingCart shoppingCart = new ShoppingCart();

        // act
        shoppingCartService.addProductToShoppingCart(shoppingCart, TestUtils.product1);
        shoppingCartService.addProductToShoppingCart(shoppingCart, TestUtils.product2);

        // assert
        Assert.assertNotNull(shoppingCart.getCartItems());
        Assert.assertEquals(2, shoppingCart.getCartItems().size());

        CartItem cartItem1 = shoppingCart.getCartItems().get(0);
        CartItem cartItem2 = shoppingCart.getCartItems().get(1);
        Assert.assertEquals(1, cartItem1.getQuantity());
        Assert.assertEquals(1, cartItem2.getQuantity());

        Product productResult1 = cartItem1.getProduct();
        Assertions.assertEquals(10, productResult1.getPrice());
        Assert.assertEquals("001", productResult1.getId());
        Assert.assertEquals("test product1", productResult1.getName());

        Product productResult2 = cartItem2.getProduct();
        Assertions.assertEquals(15, productResult2.getPrice());
        Assert.assertEquals("002", productResult2.getId());
        Assert.assertEquals("test product2", productResult2.getName());
    }

    @Test
    void initializeShoppingCartPrice_withoutProducts() {
        // arrange
        ShoppingCart shoppingCart = new ShoppingCart();

        // act
        shoppingCartService.initializeShoppingCartPrice(shoppingCart);

        // assert
        Assertions.assertEquals(0, shoppingCart.getInitialPrice());
    }

    @Test
    void initializeShoppingCartPrice_withThreeProducts() {
        // arrange
        ShoppingCart shoppingCart = TestUtils.generateShoppingCart_twoProduct1_oneProduct2();

        // act
        shoppingCartService.initializeShoppingCartPrice(shoppingCart);

        // assert
        Assertions.assertEquals(35, shoppingCart.getInitialPrice());
    }

    @Test
    void finalizeShoppingCart_withoutProducts() {
        // arrange
        ShoppingCart shoppingCart = new ShoppingCart();

        // act
        shoppingCartService.finalizeShoppingCart(shoppingCart);

        // assert
        Assertions.assertEquals(0, shoppingCart.getFinalPrice());
    }

    @Test
    void finalizeShoppingCart_withThreeProducts() {
        // arrange
        ShoppingCart shoppingCart = TestUtils.generateShoppingCart_twoProduct1_oneProduct2();

        // act
        shoppingCartService.finalizeShoppingCart(shoppingCart);

        // assert
        Assertions.assertEquals(35, shoppingCart.getFinalPrice());
    }

    @Test
    void finalizeShoppingCart_withThreeProducts_discount() {
        // arrange
        ShoppingCart shoppingCart = TestUtils.generateShoppingCart_threeProduct1_discount();

        // act
        shoppingCartService.finalizeShoppingCart(shoppingCart);

        // assert
        Assertions.assertEquals(27, shoppingCart.getFinalPrice());
    }

}
