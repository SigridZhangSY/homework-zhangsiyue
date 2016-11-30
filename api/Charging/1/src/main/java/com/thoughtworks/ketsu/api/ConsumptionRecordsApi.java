package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.consumptionRecord.CallRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.ConsumptionRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.DataRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.SmsRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ConsumptionRecordsApi {

    private Card card;

    public ConsumptionRecordsApi(Card card) {
        this.card = card;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsumptionRecord> getConsumptionRecordsList(){
        return card.getConsumptionRecords();
    }

    @GET
    @Path("{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ConsumptionRecord getConsumptionRecord(@PathParam("cid")long cid){

        Optional<ConsumptionRecord> consumptionRecord = card.getConsumptionRecordById(cid);

        if(!consumptionRecord.isPresent())
            throw new NotFoundException();

        return consumptionRecord.get();
    }

    @GET
    @Path("call-records")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CallRecord> getCallRecordsList(){
        List<ConsumptionRecord> consumptionRecords = card.getConsumptionRecords();
        List<CallRecord> callRecords = consumptionRecords.stream()
                .filter(c -> c instanceof CallRecord).map(c -> (CallRecord) c)
                .collect(Collectors.toList());
        return callRecords;
    }

    @GET
    @Path("data-records")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DataRecord> getDataRecordsList(){
        List<ConsumptionRecord> consumptionRecords = card.getConsumptionRecords();
        List<DataRecord> dataRecords = consumptionRecords.stream()
                .filter(c -> c instanceof DataRecord).map(c -> (DataRecord) c)
                .collect(Collectors.toList());
        return dataRecords;
    }

    @GET
    @Path("sms-records")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SmsRecord> getSmsRecordsList(){
        List<ConsumptionRecord> consumptionRecords = card.getConsumptionRecords();
        List<SmsRecord> smsRecords = consumptionRecords.stream()
                .filter(c -> c instanceof SmsRecord).map(c -> (SmsRecord) c)
                .collect(Collectors.toList());
        return smsRecords;
    }
}
