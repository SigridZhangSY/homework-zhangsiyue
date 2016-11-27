package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.Map;
import rich.Player;
import rich.place.Estate;
import rich.place.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitCommandHandlerSellToolTest {
    private Dice dice;
    private Map map;
    private Player player;

    final double INIT_BALANCE = 1000;
    final int INIT_POINTS = 1000;
    private Place start;

    @Before
    public void setUp() throws Exception {
        map = mock(Map.class);
        dice = mock(Dice.class);
        start = mock(Place.class);
        player = Player.createPlayerWithPoints(new WaitCommandHandler(map, dice), start, INIT_BALANCE, INIT_POINTS);
    }

    @Test
    public void should_return_wait_command_handler_after_sell_tool() throws Exception {
        player.buyTool(0);
        int prePoints = player.getPoints();
        assertThat(player.getTools().size(), is(1));

        player.executed("sellTool 1");

        assertThat(player.getHandler() instanceof WaitCommandHandler, is(true));
        assertThat(player.getTools().size(), is(0));
        assertThat(player.getPoints(), is(prePoints + 50));
    }

    @Test
    public void should_not_sell_not_owned_tool() throws Exception {
        player.buyTool(1);
        int prePoints = player.getPoints();
        assertThat(player.getTools().size(), is(1));

        player.executed("sellTool 1");

        assertThat(player.getHandler() instanceof WaitCommandHandler, is(true));
        assertThat(player.getTools().size(), is(1));
        assertThat(player.getPoints(), is(prePoints));
    }
}
