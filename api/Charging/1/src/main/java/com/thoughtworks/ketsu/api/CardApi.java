package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Plan;
import com.thoughtworks.ketsu.domain.card.Card;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

public class CardApi {
    private Card card;

    public CardApi(Card card) {
        this.card = card;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Card getCard(){
        return card;
    }

    @Path("plans")
    public PlansApi getPlansApi(){
        return new PlansApi(card);
    }
}
