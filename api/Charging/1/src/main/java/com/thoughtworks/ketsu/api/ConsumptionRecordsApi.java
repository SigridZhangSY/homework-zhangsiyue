package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.CurrentUser;
import com.thoughtworks.ketsu.domain.consumptionRecord.CallRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.ConsumptionRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.DataRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.SmsRecord;
import com.thoughtworks.ketsu.util.Validators.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.thoughtworks.ketsu.util.Validators.all;
import static com.thoughtworks.ketsu.util.Validators.fieldNotEmpty;
import static com.thoughtworks.ketsu.util.Validators.validate;

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

    @POST
    @Path("call-records")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCallRecord(Map<String, Object> info,
                                     @Context Routes routes,
                                     @Context CurrentUser currentUser){
        if(!currentUser.isUserHimself(card.getId()))
            throw new ForbiddenException();

        Validator callRecordValidator =
                all(fieldNotEmpty("date", "date is required"),
                        fieldNotEmpty("duration", "duration is required"),
                        fieldNotEmpty("callNumber", "callNumber is required")
                );

        validate(callRecordValidator, info);


        CallRecord callRecord = card.createCallRecord(info);

        return Response.created(routes.consumptionUrl(callRecord)).build();
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

    @POST
    @Path("data-records")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDataRecord(Map<String, Object> info,
                                     @Context Routes routes,
                                     @Context CurrentUser currentUser){
        if(!currentUser.isUserHimself(card.getId()))
            throw new ForbiddenException();

        Validator callRecordValidator =
                all(fieldNotEmpty("date", "date is required"),
                        fieldNotEmpty("volume", "volume is required")
                );

        validate(callRecordValidator, info);

        DataRecord dataRecord = card.createDataRecord(info);

        return Response.created(routes.consumptionUrl(dataRecord)).build();
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

    @POST
    @Path("sms-records")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSmsRecord(Map<String, Object> info,
                                    @Context Routes routes,
                                    @Context CurrentUser currentUser){

        if(!currentUser.isUserHimself(card.getId()))
            throw new ForbiddenException();

        Validator callRecordValidator =
                all(fieldNotEmpty("date", "date is required"),
                        fieldNotEmpty("number", "number is required")
                );

        validate(callRecordValidator, info);

        SmsRecord smsRecord = card.createSmsRecord(info);

        return Response.created(routes.consumptionUrl(smsRecord)).build();
    }

}
