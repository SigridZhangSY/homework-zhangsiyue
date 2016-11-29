package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Payment implements Record {
    private double amount;
    private String date;
    private Plan plan;
    private long id;


    public static Payment createPayment(double amount, String date, Plan plan, long id) {
        Payment payment = new Payment();
        payment.amount = amount;
        payment.date = date;
        payment.plan = plan;
        payment.id = id;
        return payment;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("amount", amount);
            put("date", date);
            put("uri", routes.paymentUrl(Payment.this));
        }};
    }

    public Plan getPlan() {
        return plan;
    }

    public long getId() {
        return id;
    }
}
