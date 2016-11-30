package com.thoughtworks.ketsu.domain.price;

import com.thoughtworks.ketsu.api.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class FreeCallPrice implements BalancePrice {
    private double price;

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("price", price);
            put("type", "FREE_CALL");
        }};
    }

    @Override
    public double getPrice() {
        return price;
    }
}
