package com.thoughtworks.ketsu.domain.consumptionRecord;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.price.BalancePrice;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class SmsRecord implements ConsumptionRecord, Record {
    private long id;
    private Card receiver;
    private String date;
    private BalancePrice price;

    @Override
    public Card getCard() {
        return receiver;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.consumptionUrl(SmsRecord.this));
            put("receiver", receiver);
            put("date", date);
            put("price", price.toJson(routes));
        }};
    }
}
