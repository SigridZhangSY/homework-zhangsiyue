package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Card;
import com.thoughtworks.ketsu.domain.consumptionRecord.DataRecord;
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
public class ConsumptionRecordsApiTest extends ApiSupport{
    Card card;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        card = mock(Card.class);
        when(cards.getCardById(anyInt())).thenReturn(Optional.of(card));
        when(currentUser.isAdmin()).thenReturn(true);
    }

    @Test
    public void should_return_200_and_consumption_detail() throws Exception {
        DataRecord dataRecord = TestHelper.getADataRecord();
        when(card.getConsumptionRecordById(anyInt())).thenReturn(Optional.of(dataRecord));

        Response get = get("cards/1/consumption-records/1");

        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("uri").toString().contains("/consumption-records/1"), is(true));
        assertThat(map.get("date"), notNullValue());
        assertThat(map.get("price"), notNullValue());
        assertThat(map.get("volume"), notNullValue());
    }

    @Test
    public void return_404_when_consumption_record_not_existed() throws Exception {
        when(card.getConsumptionRecordById(anyInt())).thenReturn(Optional.empty());

        Response get = get("cards/1/consumption-records/1");

        assertThat(get.getStatus(), is(404));
    }

    @Test
    public void should_return_403_when_try_to_get_consumption_record_of_other_card() throws Exception {
        when(currentUser.isAdmin()).thenReturn(false);
        when(currentUser.isUserHimself(anyInt())).thenReturn(false);

        Response get = get("cards/1/consumption-records/1");

        assertThat(get.getStatus(), is(403));
    }
}
