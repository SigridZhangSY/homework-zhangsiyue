package com.thoughtworks.ketsu.api.jersey;


import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Plan;
import com.thoughtworks.ketsu.domain.Recharge;
import com.thoughtworks.ketsu.domain.consumptionRecord.CallRecord;
import com.thoughtworks.ketsu.domain.consumptionRecord.ConsumptionRecord;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

//    public URI userUrl(User user) {
//        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
//    }

    public URI cardUrl(Card card) {
        return URI.create(String.format("%scards/%s", baseUri, card.getId()));
    }

    public URI planUrl(Plan plan) {
        return URI.create(String.format("%scards/%s/plans/%s", baseUri, plan.getCard().getId(), plan.getId()));
    }

    public URI paymentUrl(Payment payment) {
        return URI.create(String.format("%scards/%s/plans/%s/payments/%s", baseUri, payment.getPlan().getCard().getId(), payment.getPlan().getId(), payment.getId()));
    }

    public URI rechargeUrl(Recharge recharge) {
        return URI.create(String.format("%scards/%s/recharges/%s", baseUri, recharge.getCard().getId(), recharge.getId()));
    }

    public URI consumptionUrl(ConsumptionRecord consumptionRecord) {
        return URI.create(String.format("%scards/%s/consumption-records/%s", baseUri, consumptionRecord.getCard().getId(), consumptionRecord.getId()));
    }
}
