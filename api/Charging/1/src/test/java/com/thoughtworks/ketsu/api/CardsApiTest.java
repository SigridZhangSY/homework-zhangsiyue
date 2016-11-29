package com.thoughtworks.ketsu.api;

import javax.ws.rs.core.Response;

import com.thoughtworks.ketsu.domain.card.Card;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class CardsApiTest extends ApiSupport {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        reset(cards);
    }

    @Test
    public void should_return_404_when_get_not_exist_card() throws Exception {
        when(cards.getCardById(anyInt())).thenReturn(Optional.empty());
        when(currentUser.isAdmin()).thenReturn(true);

        Response get = get("cards/1");

        assertThat(get.getStatus(), is(404));
    }

    @Test
    public void should_return_200_and_detail_when_find_card() throws Exception {
        Card card = TestHelper.getACard();
        when(cards.getCardById(anyInt())).thenReturn(Optional.of(card));
        when(currentUser.isAdmin()).thenReturn(true);

        Response get = get("cards/1");
        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("uri"), notNullValue());
        assertThat(map.get("id"), notNullValue());
        assertThat(map.get("number"), notNullValue());
        assertThat(map.get("location"), notNullValue());
        assertThat(map.get("balance"), notNullValue());
    }

    @Test
    public void should_403_when_other_user_try_to_get_user_detail() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1");
        assertThat(get.getStatus(), is(403));
    }

    @Test
    public void should_return_403_when_non_admin_create_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);

        Response post = post("cards", new HashMap<>());

        assertThat(post.getStatus(), is(403));
    }
}
