package com.example.ipitsik.entity;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String name;
    private double price;
    private double discountPrice;

}
