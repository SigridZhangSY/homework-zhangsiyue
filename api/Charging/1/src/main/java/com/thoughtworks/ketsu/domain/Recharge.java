package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Recharge implements Record {
    private double amount;
    private String date;
    private long id;
    private Card card;

    public static Recharge createRecharge(double amount, String date, long id, Card card){
        Recharge recharge = new Recharge();
        recharge.amount = amount;
        recharge.date = date;
        recharge.id = id;
        recharge.card = card;
        return recharge;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.rechargeUrl(Recharge.this));
            put("amount", amount);
            put("date", date);
        }};
    }

    public long getId() {
        return id;
    }

    public Card getCard() {
        return card;
    }
}
