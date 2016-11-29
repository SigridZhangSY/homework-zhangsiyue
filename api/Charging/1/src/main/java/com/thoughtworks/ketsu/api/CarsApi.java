package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.card.Card;
import com.thoughtworks.ketsu.domain.card.Cards;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("cards")
public class CarsApi {

    @GET
    @Path("{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Card getCard(@PathParam("cid") long cid,
                        @Context Cards cards) {

        Optional<Card> card = cards.getCardById(cid);

        throw new NotFoundException();
    }
}
