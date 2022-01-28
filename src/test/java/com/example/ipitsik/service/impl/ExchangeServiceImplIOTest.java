package com.example.ipitsik.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeServiceImplIOTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init() {
    }

    @Test
    void exchange() throws URISyntaxException {
        // arrange
        String REQUEST_URL = "https://free.currconv.com/api/v7/convert?compact=ultra&apiKey=e0af0a90b8051f3b50b8&q=GBP_EUR";

        // act
        LinkedHashMap<String, Double> linkedHashMap = restTemplate
                .getForObject(new URI(REQUEST_URL), LinkedHashMap.class);

        // assert
        Assertions.assertNotNull(linkedHashMap.get("GBP_EUR"));
    }
}
