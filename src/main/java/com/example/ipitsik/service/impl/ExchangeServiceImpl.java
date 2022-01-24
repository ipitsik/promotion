package com.example.ipitsik.service.impl;

import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.ExchangeService;
import com.example.ipitsik.utils.Constants;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Value( "${exchange.converterUrl}" )
    private String converterUrl;

    @Value( "${exchange.enabled}" )
    private boolean enabled;

    private final RestTemplate restTemplate;

    @Override
    public double exchange(CurrencyEnum currency) throws URISyntaxException, ExchangeException {
        if(!enabled){
            if(!currency.toString().equals(Constants.GBP)){
                throw new ExchangeException("Exchange Service is not enabled");
            } else {
                return 1;
            }
        }
        LinkedHashMap<String, Double> linkedHashMap = restTemplate.getForObject(new URI(converterUrl), LinkedHashMap.class);
        if(linkedHashMap == null || linkedHashMap.get(getKey(currency)) == null){
            throw new ExchangeException("Something went wrong with currency exchange");
        } else {
            return linkedHashMap.get(getKey(currency)).doubleValue();
        }
    }

    private String getKey(CurrencyEnum currency){
        return "GBP_" + currency;
    }
}
