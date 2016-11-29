package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.card.Card;
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
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class PlansApiTest  extends ApiSupport {

    private Card card = mock(Card.class);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(cards.getCardById(anyInt())).thenReturn(Optional.of(card));
        when(currentUser.isAdmin()).thenReturn(true);
    }

    @Test
    public void should_return_404_when_not_find_plan() throws Exception {
        when(card.getPlansById(anyInt())).thenReturn(Optional.empty());

        Response get = get("cards/1/plans/1");

        assertThat(get.getStatus(), is(404));
    }

    @Test
    public void should_not_get_plan_of_other_user() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/plans/1");

        assertThat(get.getStatus(), is(403));
    }

    @Test
    public void should_return_200_and_detail_when_find_plan() throws Exception {
        when(card.getPlansById(anyInt())).thenReturn(Optional.of(TestHelper.getAPlan()));

        Response get = get("cards/1/plans/1");

        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("uri"), notNullValue());
        assertThat(map.get("name"), notNullValue());
        assertThat(map.get("freeCall"), notNullValue());
        assertThat(map.get("freeData"), notNullValue());
        assertThat(map.get("freeSMS"), notNullValue());
        assertThat(map.get("callPrice"), notNullValue());
        assertThat(map.get("dataPrice"), notNullValue());
        assertThat(map.get("smsPrice"), notNullValue());
        assertThat(map.get("timeLimit"), notNullValue());
        assertThat(map.get("date"), notNullValue());
        assertThat(map.get("totalPrice"), notNullValue());
    }

    @Test
    public void should_return_403_when_try_to_get_plan_list_of_other_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/plans");

        assertThat(get.getStatus(), is(403));
    }
}
