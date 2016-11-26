package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.Map;
import rich.Player;
import rich.place.Estate;
import rich.place.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitCommandHandlerSellTest {
    private Dice dice;
    private Map map;
    private Player player;

    final double INIT_BALANCE = 1000;
    final double IN_BALANCE = 200;
    private Estate estate;

    @Before
    public void setUp() throws Exception {
        map = mock(Map.class);
        dice = mock(Dice.class);
        estate = new Estate(IN_BALANCE);
        player = new Player(new WaitCommandHandler(map, dice), estate, INIT_BALANCE);
        when(dice.next()).thenReturn(1);
    }

    @Test
    public void should_return_wait_command_handler_after_sell_estate() throws Exception {
        player.buyEmpty();
        when(map.getPlace(anyInt())).thenReturn(estate);

       player.executed("sell 1");

        assertThat(player.getEstates().size(), is(0));
        assertThat(player.getBalance(), is(INIT_BALANCE + IN_BALANCE));
        assertThat(estate.getOwner(), is(nullValue()));
        assertThat(player.getHandler() instanceof WaitCommandHandler, is(true));
    }

    @Test
    public void should_not_sell_not_owned_estate() throws Exception {
        when(map.getPlace(anyInt())).thenReturn(estate);

        assertThat(estate.getOwner(), is(nullValue()));

        player.executed("sell 1");

        assertThat(player.getEstates().size(), is(0));
        assertThat(player.getBalance(), is(INIT_BALANCE));
        assertThat(player.getHandler() instanceof WaitCommandHandler, is(true));
    }
}
