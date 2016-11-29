package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Plan implements Record {
    private double freeCall;
    private double freeData;
    private int freeSMS;
    private double callPrice;
    private double dataPrice;
    private double smsPrice;
    private double timeLimit;
    private double totalPrice;
    private String date;
    private String uri;
    private String name;


    public static Plan createPlan(double freeCall, double freeData, int freeSMS, double callPrice, double dataPrice, double smsPrice, double timeLimit, double totalPrice, String date, String uri, String name) {
        Plan plan = new Plan();
        plan.freeCall = freeCall;
        plan.freeData = freeData;
        plan.freeSMS = freeSMS;
        plan.callPrice = callPrice;
        plan.dataPrice = dataPrice;
        plan.smsPrice = smsPrice;
        plan.timeLimit = timeLimit;
        plan.totalPrice = totalPrice;
        plan.date = date;
        plan.uri = uri;
        plan.name = name;
        return plan;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", uri);
            put("name", name);
            put("freeCall", freeCall);
            put("freeData", freeData);
            put("freeSMS", freeSMS);
            put("timeLimit", timeLimit);
            put("callPrice", callPrice);
            put("dataPrice", dataPrice);
            put("smsPrice", smsPrice);
            put("date", date);
            put("totalPrice", totalPrice);
        }};
    }
}
