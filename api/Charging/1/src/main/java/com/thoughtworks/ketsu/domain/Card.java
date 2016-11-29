package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Card implements Record{
    private long id;
    private String number;
    private Location location;
    private Balance balance;

    public Card(long id, String number, Location location, Balance balance) {
        this.id = id;
        this.number = number;
        this.location = location;
        this.balance = balance;
    }

    public Optional<Plan> getPlansById(long id){
        return null;
    }

    public List<Plan> getPlans() {
        return null;
    }

    public Optional<Recharge> getRechargeById(long id){
        return null;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.cardUrl(Card.this));
            put("id", id);
            put("number", number);
            put("location", location.toJson(routes));
            put("balance", balance.toJson(routes));
        }};
    }

    public long getId() {
        return id;
    }

    public Balance getBalance() {
        return balance;
    }
}
