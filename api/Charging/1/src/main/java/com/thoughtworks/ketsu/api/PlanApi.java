package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.PaymentsApi;
import com.thoughtworks.ketsu.domain.Plan;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

public class PlanApi {
    private Plan plan;

    public PlanApi(Plan plan) {
        this.plan = plan;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Plan getPlan() {
        return plan;
    }

    @Path("payments")
    public PaymentsApi getPaymentsApi(){
        return new PaymentsApi(plan);
    }

}
