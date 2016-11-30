package com.thoughtworks.ketsu.domain.consumptionRecord;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.price.BalancePrice;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class SmsRecord implements ConsumptionRecord, Record {
    private long id;
    private String receiverNumber;
    private String date;
    private BalancePrice price;
    private Card card;

    public static SmsRecord createSmsRecord(long id, String receiverNumber, String date, BalancePrice price, Card card){
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.id = id;
        smsRecord.receiverNumber = receiverNumber;
        smsRecord.price = price;
        smsRecord.card = card;
        return smsRecord;
    }

    @Override
    public Card getCard() {
        return card;
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
            put("receiver", receiverNumber);
            put("date", date);
            put("price", price.toJson(routes));
        }};
    }
}
