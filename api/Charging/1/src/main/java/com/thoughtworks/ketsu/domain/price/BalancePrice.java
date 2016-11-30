package com.thoughtworks.ketsu.domain.price;

import com.thoughtworks.ketsu.infrastructure.records.Record;

public interface BalancePrice extends Record {

    double getPrice();
}
