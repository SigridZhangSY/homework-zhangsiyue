package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.Recharge;
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
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class RechargesApiTest extends ApiSupport {

    private Card card = mock(Card.class);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(cards.getCardById(anyInt())).thenReturn(Optional.of(card));
        when(currentUser.isAdmin()).thenReturn(true);
    }

    @Test
    public void should_return_200_and_detail_when_find_recharge_by_id() throws Exception {
        Recharge recharge = TestHelper.getARecharge();
        when(card.getRechargeById(anyInt())).thenReturn(Optional.of(recharge));

        Response get = get("cards/1/recharges/1");

        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("uri").toString().contains("/recharges/1"), is(true));
        assertThat(map.get("amount"), notNullValue());
        assertThat(map.get("date"), notNullValue());
    }

    @Test
    public void should_return_404_when_recharge_not_exist() throws Exception {
        when(card.getRechargeById(anyInt())).thenReturn(Optional.empty());

        Response get = get("cards/1/recharges/1");

        assertThat(get.getStatus(), is(404));
    }

    @Test
    public void should_return_403_when_try_to_get_recharge_of_other_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/recharges/1");

        assertThat(get.getStatus(), is(403));
    }

    @Test
    public void should_return_200_when_try_to_get_recharge_list() throws Exception {
        when(card.getRecharges()).thenReturn(asList(TestHelper.getARecharge()));

        Response get = get("cards/1/recharges");

        assertThat(get.getStatus(), is(200));
        List list = get.readEntity(List.class);
        assertThat(list.size(), is(1));
        Map map = (Map)list.get(0);
        assertThat(map.get("uri").toString().contains("recharges/1"), is(true));
        assertThat(map.get("amount"), notNullValue());
        assertThat(map.get("date"), notNullValue());
    }

    @Test
    public void should_return_403_when_try_to_get_recharge_list_of_other_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/recharges");

        assertThat(get.getStatus(), is(403));
    }

    @Test
    public void should_return_201_when_create_recharge() throws Exception {
        when(currentUser.isUserHimself(anyInt())).thenReturn(true);
        when(card.createRecharge(anyMap())).thenReturn(TestHelper.getARecharge());

        Response post = post("cards/1/recharges", TestHelper.rechargeMap(100, "20160101"));

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toString().contains("/recharges/1"), is(true));
    }

    @Test
    public void should_return_400_when_create_recharge_with_invalid_parameter() throws Exception {
        when(currentUser.isUserHimself(anyInt())).thenReturn(true);

        Response post = post("cards/1/recharges", new HashMap<>());

        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_return_403_when_try_to_create_recharge_of_other_card() throws Exception {
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response post = post("cards/1/recharges", TestHelper.rechargeMap(100, "20160101"));

        assertThat(post.getStatus(), is(403));
    }
}
