package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.exception.ConflictException;
import com.thoughtworks.ketsu.api.jersey.CardApi;
import com.thoughtworks.ketsu.domain.CurrentUser;
import com.thoughtworks.ketsu.domain.card.Card;
import com.thoughtworks.ketsu.domain.card.Cards;
import org.glassfish.grizzly.http.server.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("cards")
public class CardsApi {

    @Path("{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public CardApi getCardApi(@PathParam("cid") long cid,
                        @Context Cards cards,
                        @Context CurrentUser currentUser) {
        if (!currentUser.isAdmin() && !currentUser.isUserHimself(cid))
            throw new ForbiddenException();
        Optional<Card> card = cards.getCardById(cid);

        if (!card.isPresent())
            throw new NotFoundException();

        return new CardApi(card.get());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCard(@Context CurrentUser currentUser){
        if(!currentUser.isAdmin())
            throw new ForbiddenException();
        return null;
    }
}
