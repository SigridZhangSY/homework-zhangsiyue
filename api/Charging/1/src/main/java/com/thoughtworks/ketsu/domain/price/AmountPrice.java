package com.thoughtworks.ketsu.domain.price;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.price.BalancePrice;

import java.util.HashMap;
import java.util.Map;

public class AmountPrice implements BalancePrice {
    private double price;

    public static AmountPrice createAmountPrice(double price){
        AmountPrice amountPrice = new AmountPrice();
        amountPrice.price = price;
        return amountPrice;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("price", price);
            put("type", "AMOUNT");
        }};
    }

    @Override
    public double getPrice() {
        return price;
    }
}
