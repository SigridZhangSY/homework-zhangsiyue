package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Plan;
import com.thoughtworks.ketsu.domain.Card;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

public class PlansApi {
    private Card card;

    public PlansApi(Card card) {
        this.card = card;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plan> getPlanForCard(){
        return card.getPlans();
    }

    @Path("{pid}")
    public PlanApi getPlanById(@PathParam("pid") long pid) {
        Optional<Plan> plan = card.getPlansById(pid);
        if (!plan.isPresent())
            throw new NotFoundException();
        return new PlanApi(plan.get());
    }
}
