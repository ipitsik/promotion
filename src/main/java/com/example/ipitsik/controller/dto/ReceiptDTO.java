package com.example.ipitsik.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ReceiptDTO {

    private List<CartItemDTO> cartItems;
    private String initialPrice;
    private String totalDiscount;
    private String finalPrice;

}
