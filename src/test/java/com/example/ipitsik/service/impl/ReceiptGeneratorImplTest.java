package com.example.ipitsik.service.impl;

import com.example.ipitsik.controller.dto.ReceiptDTO;
import com.example.ipitsik.entity.ShoppingCart;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.ExchangeService;
import com.example.ipitsik.utils.CurrencyEnum;
import com.example.ipitsik.utils.TestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

@SpringBootTest
class ReceiptGeneratorImplTest {

    @Mock
    private ExchangeService exchangeService;

    @InjectMocks
    private ReceiptGeneratorImpl receiptGenerator;

    @BeforeEach
    public void init() throws ExchangeException, URISyntaxException {
        Mockito.when(exchangeService.exchange(CurrencyEnum.EUR, CurrencyEnum.USD)).thenReturn(1.20);
        Mockito.when(exchangeService.exchange(CurrencyEnum.EUR, CurrencyEnum.EUR)).thenReturn(1.0);
    }

    @Test
    void generateReceipt_shoppingCart_threeProducts1() throws ExchangeException, URISyntaxException {
        // arrange
        ShoppingCart shoppingCart = TestUtils.generateShoppingCart_threeProduct1();

        // act
        ReceiptDTO receiptDTO = receiptGenerator.generateReceipt(shoppingCart, CurrencyEnum.EUR, CurrencyEnum.EUR);

        // assert
        Assert.assertNotNull(receiptDTO);
        Assert.assertEquals("€30.00", receiptDTO.getInitialPrice());
        Assert.assertEquals("€30.00", receiptDTO.getFinalPrice());
        Assert.assertEquals("0.0%", receiptDTO.getTotalDiscount());
        Assert.assertEquals(1, receiptDTO.getCartItems().size());
        Assert.assertEquals("No currency exchange needed", receiptDTO.getMessage());
    }

    @Test
    void generateReceipt_shoppingCart_threeProducts1withUSDexchange() throws ExchangeException, URISyntaxException {
        // arrange
        ShoppingCart shoppingCart = TestUtils.generateShoppingCart_threeProduct1();

        // act
        ReceiptDTO receiptDTO = receiptGenerator.generateReceipt(shoppingCart, CurrencyEnum.EUR, CurrencyEnum.USD);

        // assert
        Assert.assertNotNull(receiptDTO);
        Assert.assertEquals("$36.00", receiptDTO.getInitialPrice());
        Assert.assertEquals("$36.00", receiptDTO.getFinalPrice());
        Assert.assertEquals("0.0%", receiptDTO.getTotalDiscount());
        Assert.assertEquals(1, receiptDTO.getCartItems().size());
        Assert.assertEquals("1 EUR equals 1.2 USD", receiptDTO.getMessage());
    }

}
