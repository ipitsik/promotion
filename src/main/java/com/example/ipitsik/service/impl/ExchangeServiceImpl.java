package com.example.ipitsik.service.impl;

import com.example.ipitsik.config.ExchangeConfiguration;
import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.service.ExchangeService;
import com.example.ipitsik.utils.Constants;
import com.example.ipitsik.utils.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeConfiguration exchangeConfiguration;

    private final RestTemplate restTemplate;

    @Override
    public double exchange(CurrencyEnum fromCurrency, CurrencyEnum toCurrency) throws URISyntaxException, ExchangeException {
        if (fromCurrency.equals(toCurrency)) {
            return 1;
        }
        if (!exchangeConfiguration.isEnabled()) {
            throw new ExchangeException("Exchange Service is not enabled");
        }
        LinkedHashMap<String, Double> linkedHashMap = restTemplate
                .getForObject(new URI(generateUrl(fromCurrency, toCurrency)), LinkedHashMap.class);
        if (linkedHashMap == null || linkedHashMap.get(getKeyConvert(fromCurrency, toCurrency)) == null) {
            throw new ExchangeException("Something went wrong with currency exchange");
        } else {
            return linkedHashMap.get(getKeyConvert(fromCurrency, toCurrency));
        }
    }

    private String generateUrl(CurrencyEnum fromCurrency, CurrencyEnum toCurrency) {
        return exchangeConfiguration.getRequestUrl() +
                Constants.HTTP_REQUEST_SEPARATOR +
                Constants.COMPACT + Constants.EQUALS + exchangeConfiguration.getCompact() +
                Constants.HTTP_PARAMS_SEPARATOR +
                Constants.API_KEY + Constants.EQUALS + exchangeConfiguration.getApiKey() +
                Constants.HTTP_PARAMS_SEPARATOR +
                Constants.QUERY + Constants.EQUALS + getKeyConvert(fromCurrency, toCurrency);
    }

    private String getKeyConvert(CurrencyEnum fromCurrency, CurrencyEnum toCurrency) {
        return fromCurrency.toString() + "_" + toCurrency.toString();
    }
}
