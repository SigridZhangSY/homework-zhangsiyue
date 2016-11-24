package com.thoughtworks.ketsu.domain.product;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product implements Record{
    private long id;
    private String name;
    private List<ProductPrice> priceHistory;
    private User owner;

    public Product(long id, String name, double price, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        priceHistory = new ArrayList<ProductPrice>(){{
            add(new ProductPrice(0, price));
        }};
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
            put("price", priceHistory.get(priceHistory.size() - 1).getPrice());
        }};
    }

    public long getId() {
        return id;
    }

    public void changePrice(double price){
    }
}
