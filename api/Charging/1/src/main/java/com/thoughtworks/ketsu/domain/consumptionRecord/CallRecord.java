package com.thoughtworks.ketsu.domain.consumptionRecord;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.price.BalancePrice;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class CallRecord implements ConsumptionRecord, Record {
    private long id;
    private BalancePrice price;
    private String date;
    private double duration;
    private String callNumber;
    private Card caller;
    private boolean isRoaming;

    public static CallRecord createCallRecord(long id,
                                              BalancePrice price,
                                              String date,
                                              double duration,
                                              String callNumber,
                                              Card caller,
                                              boolean isRoaming
                                                ){
        CallRecord callRecord = new CallRecord();
        callRecord.id = id;
        callRecord.price = price;
        callRecord.date = date;
        callRecord.duration = duration;
        callRecord.callNumber = callNumber;
        callRecord.caller = caller;
        callRecord.isRoaming =isRoaming;
        return callRecord;
    }
    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.consumptionUrl(CallRecord.this));
            put("id", id);
            put("date", date);
            put("callNumber", callNumber);
            put("duration", duration);
            put("isRoaming", isRoaming);
            put("price", price.toJson(routes));
        }};
    }

    public Card getCard() {
        return caller;
    }

    public long getId() {
        return id;
    }
}
