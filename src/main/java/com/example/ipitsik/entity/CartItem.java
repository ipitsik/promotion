package com.example.ipitsik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {

    @JsonIgnore
    private double finalPrice;
    private String finalPriceReceipt;
    private int quantity;
    private Product product;

}
