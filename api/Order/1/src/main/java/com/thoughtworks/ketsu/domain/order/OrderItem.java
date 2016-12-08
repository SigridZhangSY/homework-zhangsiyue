package com.thoughtworks.ketsu.domain.order;

import com.thoughtworks.ketsu.domain.product.Product;

public class OrderItem {
    private Product product;
    private int quantity;

    public double getTotalPrice(){
        return product.getPriceOfTheTime() * quantity;
    }
}
