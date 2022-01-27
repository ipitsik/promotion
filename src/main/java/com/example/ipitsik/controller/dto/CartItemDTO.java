package com.example.ipitsik.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {

    private String finalItemPrice;
    private int quantity;
    private ProductReceiptDTO product;

}
