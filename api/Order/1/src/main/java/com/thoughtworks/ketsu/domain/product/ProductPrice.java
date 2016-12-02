package com.thoughtworks.ketsu.domain.product;

public class ProductPrice{
    private long id;
    private double price;

    public ProductPrice(){}

    public ProductPrice(long id, double price) {
        this.id = id;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
