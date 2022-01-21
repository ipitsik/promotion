package com.example.ipitsik.entity;

import lombok.Data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {

    private double initialCost;
    private String finalCost;
    private List<ProductItem> productItems;
    private double totalDiscount;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void addProductToShoppingCart(Product product){

        if(productItems != null && !productItems.isEmpty()) {
            for (ProductItem productItem : productItems) {
                if (productItem.getProduct().getName().equals(product.getName())) {
                    productItem.increaseQuantity();
                    return;
                }
            }
        }else{
            productItems = new ArrayList<>();
        }
        ProductItem productItem = new ProductItem();
        productItem.setProduct(product);
        productItem.setQuantity(1);
        productItem.setFinalPrice(product.getPrice());
        productItems.add(productItem);
    }

    public void calculateInitialCost(){
        for(ProductItem productItem : productItems){
            initialCost += productItem.getProduct().getPrice() * productItem.getQuantity();
        }
    }

    public void calculateFinalCost(){
        double finalCostd = 0;
        for(ProductItem productItem : productItems){
            finalCostd += productItem.getFinalPrice() * productItem.getQuantity();
        }
        if(totalDiscount > 0){
            finalCostd -= finalCostd * totalDiscount * 0.01;
        }
        finalCost = "£" + df.format(finalCostd);

    }
}
