package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.exception.ConflictException;
import com.thoughtworks.ketsu.domain.CurrentUser;
import com.thoughtworks.ketsu.domain.card.Card;
import com.thoughtworks.ketsu.domain.card.Cards;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("cards")
public class CardsApi {

    @GET
    @Path("{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Card getCard(@PathParam("cid") long cid,
                        @Context Cards cards,
                        @Context CurrentUser currentUser) {
        if (!currentUser.isAdmin() && !currentUser.isUserHimself(cid))
            throw new ForbiddenException();
        Optional<Card> card = cards.getCardById(cid);

        if (!card.isPresent())
            throw new NotFoundException();

        return card.get();
    }
}
