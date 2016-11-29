package com.thoughtworks.ketsu.api.jersey;

import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Plan;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class PaymentsApi {
    private Plan plan;

    public PaymentsApi(Plan plan) {
        this.plan = plan;
    }

    @GET
    @Path("{pmid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment(@PathParam("pmid")long pid){
        Payment payment = plan.findPaymentById(pid).get();
        return payment;
    }
}
