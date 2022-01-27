package com.example.ipitsik.utils;

public final class Utils {

    public static String getCurrencySymbol(CurrencyEnum currency){
        switch (currency){
            case USD: return Constants.USD_SYMBOL;
            case EUR: return Constants.EUR_SYMBOL;
            case JPY: return Constants.JPY_SYMBOL;
            default:
            case GBP: return Constants.GBP_SYMBOL;
        }
    }
}
