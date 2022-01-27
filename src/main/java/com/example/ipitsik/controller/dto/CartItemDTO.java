package com.example.ipitsik.controller.dto;

import com.example.ipitsik.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {

    private String finalItemPrice;
    private int quantity;
    private ProductReceiptDTO product;

}
