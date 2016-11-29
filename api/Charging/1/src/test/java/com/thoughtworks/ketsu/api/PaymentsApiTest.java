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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
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

    @Test
    public void should_return_404_when_payment_not_existed() throws Exception {
        when(plan.findPaymentById(anyInt())).thenReturn(Optional.empty());

        Response get = get("cards/1/plans/1/payments/1");
        assertThat(get.getStatus(), is(404));
    }

    @Test
    public void should_return_403_when_try_to_get_payment_of_other_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/plans/1/payments/1");
        assertThat(get.getStatus(), is(403));
    }

    @Test
    public void should_return_200_and_payment_list() throws Exception {
        when(plan.getPayments()).thenReturn(asList(TestHelper.getAPayment()));

        Response get = get("cards/1/plans/1/payments");
        assertThat(get.getStatus(), is(200));
        List list = get.readEntity(List.class);
        assertThat(list.size(), is(1));
        assertThat(((Map)list.get(0)).get("uri"), notNullValue());
    }

    @Test
    public void should_return_403_when_try_to_get_payment_list_of_other_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/plans/1/payments");
        assertThat(get.getStatus(), is(403));
    }

    @Test
    public void should_return_201_and_uri_when_create_payment() throws Exception {
        when(plan.createPayment()).thenReturn(TestHelper.getAPayment());
        Response post = post("/cards/1/plans/1/payments", new HashMap<>());

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toString().contains("/cards/1/plans/1/payments/1"), is(true));
    }
}
