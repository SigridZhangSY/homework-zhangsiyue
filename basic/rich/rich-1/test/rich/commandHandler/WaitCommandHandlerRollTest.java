package rich.commandHandler;

import org.junit.Test;
import rich.Dice;
import rich.Map;
import rich.Player;
import rich.place.Estate;
import rich.place.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitCommandHandlerRollTest {

    @Test
    public void should_return_wait_response_handler_when_roll_to_empty() throws Exception {
        Place start  = mock(Place.class);
        Estate target  = mock(Estate.class);
        Map map = mock(Map.class);
        Dice dice = mock(Dice.class);
        when(dice.next()).thenReturn(1);
        when(map.move(eq(start), eq(1))).thenReturn(target);
        when(target.getOwner()).thenReturn(null);
        Player player = new Player(new WaitCommandHandler(map, dice));

        player.executed("roll");
        assertThat(player.getHandler() instanceof WaitResponseHandler, is(true));
    }
}
