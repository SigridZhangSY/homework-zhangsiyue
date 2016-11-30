package com.thoughtworks.ketsu.domain.consumptionRecord;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.price.BalancePrice;
import com.thoughtworks.ketsu.infrastructure.records.Record;


public interface ConsumptionRecord{
    Card getCard();
    long getId();
}
