package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.Recharge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class RechargesApi {
    private Card card;

    public RechargesApi(Card card) {
        this.card = card;
    }

    @GET
    @Path("{rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Recharge getRechargeById(@PathParam("rid")long rid){
        Optional<Recharge> recharge = card.getRechargeById(rid);
        return recharge.get();
    }
}
