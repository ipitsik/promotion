package com.example.ipitsik.entity;

import lombok.Data;

@Data
public class ProductItem {

    private double finalPrice;
    private int quantity;
    private Product product;

    public void increaseQuantity(){
        this.setQuantity(++quantity);
    }

}
