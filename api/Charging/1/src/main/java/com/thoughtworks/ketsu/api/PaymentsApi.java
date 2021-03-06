package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.CurrentUser;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Plan;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class PaymentsApi {
    private Plan plan;

    public PaymentsApi(Plan plan) {
        this.plan = plan;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(@Context Routes routes,
                                  @Context CurrentUser currentUser){
        if (!currentUser.isUserHimself(plan.getCard().getId()))
            throw new ForbiddenException();

        Card card = plan.getCard();
        if(card.getBalance().getAmount() < plan.getTotalPrice())
            throw new BadRequestException("Balance amount is not enough");
        Payment payment = plan.createPayment();
        return Response.created(routes.paymentUrl(payment)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getPayments(){
        return plan.getPayments();
    }

    @GET
    @Path("{pmid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment(@PathParam("pmid")long pid){
        Optional<Payment> payment = plan.findPaymentById(pid);
        if (!payment.isPresent())
            throw new NotFoundException();
        return payment.get();
    }
}
