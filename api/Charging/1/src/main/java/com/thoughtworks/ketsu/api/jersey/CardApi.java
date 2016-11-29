package com.thoughtworks.ketsu.api.jersey;

import com.thoughtworks.ketsu.domain.card.Card;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
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
}
