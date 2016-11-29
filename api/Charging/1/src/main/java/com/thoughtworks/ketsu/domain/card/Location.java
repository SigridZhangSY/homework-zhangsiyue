package com.thoughtworks.ketsu.domain.card;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Location implements Record{
    private String name;
    private String url;

    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", url);
            put("name", name);
        }};
    }
}
