package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.Recharge;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

public class RechargesApi {
    private Card card;

    public RechargesApi(Card card) {
        this.card = card;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recharge> getRecharges(){
        return card.getRecharges();
    }

    @GET
    @Path("{rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Recharge getRechargeById(@PathParam("rid")long rid){

        Optional<Recharge> recharge = card.getRechargeById(rid);

        if (!recharge.isPresent())
            throw new NotFoundException();
        return recharge.get();
    }
}
