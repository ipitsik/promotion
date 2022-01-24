package com.example.ipitsik.service;

import com.example.ipitsik.exception.ExchangeException;
import com.example.ipitsik.utils.CurrencyEnum;

import java.net.URISyntaxException;

public interface ExchangeService {
    double exchange(CurrencyEnum currency) throws URISyntaxException, ExchangeException;
}
