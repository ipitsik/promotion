package com.example.ipitsik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {

    @JsonIgnore
    private double initialPrice;
    private List<CartItem> cartItems;
    @JsonIgnore
    private double totalDiscount;

    private String initialPriceInReceipt;
    private String totalDiscountInReceipt;
    private String finalPriceInReceipt;

}
