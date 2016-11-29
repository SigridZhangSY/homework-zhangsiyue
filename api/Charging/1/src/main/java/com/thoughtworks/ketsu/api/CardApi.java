package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
