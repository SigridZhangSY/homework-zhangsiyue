package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Plan;
import com.thoughtworks.ketsu.domain.card.Card;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

public class PlansApi {
    private Card card;

    public PlansApi(Card card) {
        this.card = card;
    }

    @GET
    @Path("{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Plan getPlanById(@PathParam("pid") long pid) {
        Optional<Plan> plan = card.getPlansById(pid);
        if (!plan.isPresent())
            throw new NotFoundException();
        return null;
    }
}
