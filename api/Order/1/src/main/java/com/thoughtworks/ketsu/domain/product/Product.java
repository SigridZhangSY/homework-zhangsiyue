package com.thoughtworks.ketsu.domain.product;

import com.google.inject.Inject;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product implements Record {
    private long id;
    private String name;
    private ProductPrice price;
    private User owner;

    @Inject
    ProductMapper productMapper;

    public Product(){}

    public Product(long id, String name, long product_id, double price, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.price = new ProductPrice(product_id, price);
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.productUrl(owner, Product.this));
            put("id", id);
            put("price", price.getPrice());
        }};
    }

    public long getId() {
        return id;
    }

    public void changePrice(Map<String, Object> info){
        productMapper.savePrice(id, info);
        productMapper.setPrice(id, Long.valueOf(info.get("price_id").toString()));
    }

    public String getName() {
        return name;
    }

    public ProductPrice getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }

}
