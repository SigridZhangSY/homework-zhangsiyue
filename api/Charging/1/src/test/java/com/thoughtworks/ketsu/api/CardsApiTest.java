package com.thoughtworks.ketsu.api;

import javax.ws.rs.core.Response;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
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
        Response get = get("cards/1");

        assertThat(get.getStatus(), is(404));
    }
}
