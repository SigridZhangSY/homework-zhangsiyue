package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.*;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;
    public static Map<String, Object> deployment(String appName, String releaseId) {
        return new HashMap<String, Object>() {{
            put("app", String.format("http://service-api.tw.com/apps/%s", appName));
            put("release", String.format("http://service-api.tw.com/apps/%s/releases/%s", appName, releaseId));
        }};
    }

    public static Map<String, Object> stackMap(String id, String name) {
        Map<String, Object> stackMap = new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
        return stackMap;
    }

    public static Map<String, Object> userMap(String email, String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("email", email);
        }};
    }

    public static Card getACard(){
        Location location = new Location("xian", "www.xxx/location/1.com");
        Balance balance = new Balance(10, 10, 10);
        return new Card(1, "12345678901", location, balance);
    }

    public static Plan getAPlan(){
        return Plan.createPlan(300, 1024, 100, 0.1, 0.1, 0.1, 10000, 30, "20150612", 1, "planA", getACard());
    }

    public static Payment getAPayment(){
        return Payment.createPayment(30, "20161220",getAPlan(), 1);
    }
}