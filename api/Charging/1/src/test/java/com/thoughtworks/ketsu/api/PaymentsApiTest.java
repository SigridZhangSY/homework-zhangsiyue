package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Plan;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class PaymentsApiTest extends ApiSupport {
    private Plan plan = mock(Plan.class);
    private Card card = mock(Card.class);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(cards.getCardById(anyInt())).thenReturn(Optional.of(card));
        when(currentUser.isAdmin()).thenReturn(true);
        when(card.getPlansById(anyInt())).thenReturn(Optional.of(plan));
    }

    @Test
    public void should_return_200_and_payment_detail() throws Exception {
        Payment payment = TestHelper.getAPayment();
        when(plan.findPaymentById(anyInt())).thenReturn(Optional.of(payment));

        Response get = get("cards/1/plans/1/payments/1");
        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("date"), notNullValue());
        assertThat(map.get("amount"), notNullValue());
        assertThat(map.get("uri").toString().contains("cards/1/plans/1/payments/1"), is(true));
    }
}
