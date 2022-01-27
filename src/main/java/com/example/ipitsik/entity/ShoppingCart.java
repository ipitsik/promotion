package com.example.ipitsik.entity;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {

    private double initialPrice;
    private List<CartItem> cartItems;
    private double totalDiscount;
    private double finalPrice;

}
