package com.thoughtworks.ketsu.domain.card;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Balance implements Record{

    public Balance(double amount, double freeCall, double freeData) {
        this.amount = amount;
        this.freeCall = freeCall;
        this.freeData = freeData;
    }

    private double amount;
    private double freeCall;
    private double freeData;

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("amount", amount);
            put("freeCall", freeCall);
            put("freeData", freeData);
        }};
    }
}
