package com.thoughtworks.ketsu.domain.consumptionRecord;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.price.BalancePrice;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class DataRecord implements ConsumptionRecord, Record {
    private long id;
    private BalancePrice price;
    private double volume;
    private Card card;
    private String date;

    public static DataRecord createDataRecord(long id, BalancePrice price, double volume, Card card, String date){
        DataRecord dataRecord = new DataRecord();
        dataRecord.id = id;
        dataRecord.price = price;
        dataRecord.volume = volume;
        dataRecord.card = card;
        dataRecord.date = date;
        return dataRecord;
    }

    @Override
    public Card getCard() {
        return card;
    }

    @Override
    public long getId() {
        return id;
    }

    public double getVolume(){
        return volume;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.consumptionUrl(DataRecord.this));
            put("date", date);
            put("price", price.toJson(routes));
            put("volume", volume);
        }};
    }
}
