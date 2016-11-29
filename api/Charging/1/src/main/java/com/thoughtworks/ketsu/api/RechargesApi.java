package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.Recharge;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRecharge(Map<String, Object> info,
                                   @Context Routes routes){
        Recharge recharge = card.createRecharge(info);

        return Response.created(routes.rechargeUrl(recharge)).build();
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
