package com.thoughtworks.ketsu.api.jersey;

import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Plan;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

public class PaymentsApi {
    private Plan plan;

    public PaymentsApi(Plan plan) {
        this.plan = plan;
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
