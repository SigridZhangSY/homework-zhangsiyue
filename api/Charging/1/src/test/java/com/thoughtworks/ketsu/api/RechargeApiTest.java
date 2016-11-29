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
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class RechargeApiTest extends ApiSupport {

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
}
