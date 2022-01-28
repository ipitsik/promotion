package com.example.ipitsik.service.impl;

import com.example.ipitsik.config.ExchangeConfiguration;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.utils.CurrencyEnum;
import com.example.ipitsik.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

@SpringBootTest
class ExchangeServiceImplTest {

    private static final String API_KEY = "testApiKey";
    private static final String COMPACT = "testApiKey";
    private static final String REQUEST_URL = "requestUrl";

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ExchangeConfiguration exchangeConfiguration;
    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    @BeforeEach
    public void init() throws URISyntaxException {
        Mockito.when(exchangeConfiguration.getApiKey()).thenReturn(API_KEY);
        Mockito.when(exchangeConfiguration.getCompact()).thenReturn(COMPACT);
        Mockito.when(exchangeConfiguration.getRequestUrl()).thenReturn(REQUEST_URL);
        Mockito.when(exchangeConfiguration.isEnabled()).thenReturn(true);
        Mockito.when(restTemplate.getForObject(new URI("requestUrl?compact=testApiKey&apiKey=testApiKey&q=EUR_USD"),
                LinkedHashMap.class)).thenReturn(TestUtils.generateReturnFromRestTemplate());
    }

    @Test
    void exchange() throws ExchangeException, URISyntaxException {
        // arrange

        // act
        double exchange = exchangeService.exchange(CurrencyEnum.EUR, CurrencyEnum.USD);

        // assert
        Assertions.assertEquals(1.2, exchange);
    }

    @Test
    void exchange_thenReturn1() throws URISyntaxException, ExchangeException {
        // arrange

        // act
        double exchange = exchangeService.exchange(CurrencyEnum.EUR, CurrencyEnum.EUR);

        // assert
        Assertions.assertEquals(1.0, exchange);
    }

    @Test
    void exchange_exception_wrongUrl() {
        // arrange

        // act + assert
        Assertions.assertThrows(ExchangeException.class, () -> exchangeService.exchange(CurrencyEnum.EUR, CurrencyEnum.GBP));
    }

    @Test
    void exchange_exception_exchangeNotEnabled() {
        // arrange
        Mockito.when(exchangeConfiguration.isEnabled()).thenReturn(false);

        // act + assert
        Assertions.assertThrows(ExchangeException.class, () -> exchangeService.exchange(CurrencyEnum.EUR, CurrencyEnum.USD));
    }

}
