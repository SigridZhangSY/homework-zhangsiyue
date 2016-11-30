package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.consumptionRecord.ConsumptionRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.DataRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

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
}
