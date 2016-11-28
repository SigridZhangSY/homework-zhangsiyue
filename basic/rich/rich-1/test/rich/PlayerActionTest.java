package rich;

import org.junit.Test;
import rich.place.Estate;
import rich.place.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerActionTest {

    final double INIT_BALANCE = 1000;
    final double IN_BALANCE = 200;

    @Test
    public void should_buy_empty() throws Exception {
        Estate empty = new Estate(IN_BALANCE);
        Player player = new Player(null, empty,INIT_BALANCE);

        player.buyEmpty();
        assertThat(player.getBalance(), is(INIT_BALANCE - IN_BALANCE));
        assertThat(player.getEstates().contains(empty), is(true));
        assertThat(empty.getOwner(), is(player));
    }

    @Test
    public void should_not_but_empty_without_enough_balance() throws Exception {
        Estate empty = new Estate(INIT_BALANCE + 1);
        Player player = new Player(null, empty,INIT_BALANCE);

        player.buyEmpty();
        assertThat(player.getBalance(), is(INIT_BALANCE));
        assertThat(player.getEstates().contains(empty), is(false));
        assertThat(empty.getOwner(), is(nullValue()));
    }

    @Test
    public void should_build_estate() throws Exception {
        Estate estate = new Estate(IN_BALANCE);
        Player player = new Player(null, estate, INIT_BALANCE);

        player.buildEstate();

        assertThat(player.getBalance(), is(INIT_BALANCE - IN_BALANCE));
        assertThat(estate.getLevel(), is(Estate.LEVEL.ONE));
    }

    @Test
    public void should_not_build_when_estate_is_top() throws Exception {
        Estate estate = Estate.createEstateWithLevel(IN_BALANCE, Estate.LEVEL.TOP);
        Player player = new Player(null, estate, INIT_BALANCE);

        player.buildEstate();

        assertThat(player.getBalance(), is(INIT_BALANCE));
        assertThat(estate.getLevel(), is(Estate.LEVEL.TOP));
    }

    @Test
    public void should_not_build_estate_without_enough_balance() throws Exception {
        Estate estate = Estate.createEstateWithLevel(IN_BALANCE, Estate.LEVEL.ZERO);
        Player player = new Player(null, estate, IN_BALANCE - 1);

        player.buildEstate();

        assertThat(player.getBalance(), is(IN_BALANCE - 1));
        assertThat(estate.getLevel(), is(Estate.LEVEL.ZERO));
    }

    @Test
    public void should_sell_estate() throws Exception {
        Estate estate = new Estate(IN_BALANCE);
        Player player = new Player(null, estate, INIT_BALANCE);

        player.buyEmpty();
        assertThat(player.getBalance(), is(INIT_BALANCE - IN_BALANCE));
        assertThat(player.getEstates().size(), is(1));

        player.sellEstate(estate);

        assertThat(player.getEstates().size(), is(0));
        assertThat(player.getBalance(), is(INIT_BALANCE + IN_BALANCE));
    }

    @Test
    public void should_change_record_when_arrive_and_leave_a_place() throws Exception {
        Place start = new Place();
        Place target = new Place();
        Player player = new Player(null, start);
        Map map = mock(Map.class);
        Dice dice = mock(Dice.class);
        when(dice.next()).thenReturn(1);
        when(map.move(eq(start), anyInt())).thenReturn(target);

        player.moveTo(map, dice);

        assertThat(player.getCurrentPlace(), is(target));
        assertThat(target.getPlayers().contains(player), is(true));

        when(map.move(eq(target), anyInt())).thenReturn(start);

        player.moveTo(map, dice);

        assertThat(player.getCurrentPlace(), is(start));
        assertThat(target.getPlayers().contains(player), is(false));
    }
}
