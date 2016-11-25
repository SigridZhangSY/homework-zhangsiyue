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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitCommandHandlerRollTest {

    private Dice dice;
    private Place start;
    private Map map;
    private Player player;

    @Before
    public void setUp() throws Exception {
        start  = mock(Place.class);
        map = mock(Map.class);
        dice = mock(Dice.class);
        player = new Player(new WaitCommandHandler(map, dice), start);
        when(dice.next()).thenReturn(1);
    }

    @Test
    public void should_return_wait_response_handler_when_roll_to_empty() throws Exception {
        Estate target  = mock(Estate.class);
        when(target.getOwner()).thenReturn(null);
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");
        assertThat(player.getHandler() instanceof WaitBuyResponseHandler, is(true));
        assertThat(player.getCurrentPlace(), is(target));
    }

    @Test
    public void should_return_wait_response_handler_when_roll_to_owned_estate() throws Exception {
        Estate target  = mock(Estate.class);
        when(target.getOwner()).thenReturn(player);
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");
        assertThat(player.getHandler() instanceof WaitBuildResponseHandler, is(true));
        assertThat(player.getCurrentPlace(), is(target));
    }

    @Test
    public void should_return_null_when_roll_to_other_estate() throws Exception {
        Player otherPlayer = mock(Player.class);
        Estate target  = mock(Estate.class);
        when(target.getOwner()).thenReturn(otherPlayer);
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");
        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getCurrentPlace(), is(target));
    }
}
