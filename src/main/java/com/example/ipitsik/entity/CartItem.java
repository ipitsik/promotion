package com.example.ipitsik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {

    private double finalPrice;
    private int quantity;
    private Product product;

}
