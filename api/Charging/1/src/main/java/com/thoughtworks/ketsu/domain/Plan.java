package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.Map;

public class Plan implements Record {
    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return null;
    }
}
