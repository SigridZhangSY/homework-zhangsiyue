package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.Map;
import rich.Player;
import rich.place.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitCommandHandlerUseToolTest {
    private Dice dice;
    private Map map;
    private Player player;

    final double INIT_BALANCE = 1000;
    final int INIT_POINTS = 1000;
    private Place target;

    @Before
    public void setUp() throws Exception {
        map = mock(Map.class);
        dice = mock(Dice.class);
        target = new Place();
        player = Player.createPlayerWithPoints(new WaitCommandHandler(map, dice), target, INIT_BALANCE, INIT_POINTS);
        when(map.getPlace(anyInt())).thenReturn(target);
    }

    @Test
    public void should_return_wait_command_handler_fater_use_block() throws Exception {
        player.buyTool(0);
        assertThat(player.getTools().size(), is(1));

        player.executed("block 1");

        assertThat(player.getHandler() instanceof WaitCommandHandler, is(true));
        assertThat(player.getTools().size(), is(0));
        assertThat(target.isBlocked(), is(true));
    }

    @Test
    public void should_not_use_tool_when_place_has_tool() throws Exception {
        target.setBlock();

        player.buyTool(0);
        assertThat(player.getTools().size(), is(1));

        player.executed("block 1");

        assertThat(player.getHandler() instanceof WaitCommandHandler, is(true));
        assertThat(player.getTools().size(), is(1));
        assertThat(target.isBlocked(), is(true));
    }
}
