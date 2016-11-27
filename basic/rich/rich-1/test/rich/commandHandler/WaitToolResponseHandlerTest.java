package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Player;
import rich.Tool;
import rich.place.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WaitToolResponseHandlerTest {
    private Player player;
    private Place location;
    private WaitToolResponseHandler waitToolResponseHandler = new WaitToolResponseHandler();

    final double INIT_BALANCE = 1000;
    final int INIT_POINTS = 1000;

    @Before
    public void setUp() throws Exception {
        location = mock(Place.class);
        player = Player.createPlayerWithPoints(waitToolResponseHandler, location, INIT_BALANCE, INIT_POINTS);
    }

    @Test
    public void should_buy_tool_and_wait_response_after_buy_tool() throws Exception {
        player.executed("1");

        assertThat(player.getHandler() instanceof WaitToolResponseHandler, is(true));
        assertThat(player.getTools().size(), is(1));
        assertThat(player.getTools().get(0).getType(), is(Tool.ToolType.BLOCK));
        assertThat(player.getPoints(), is(INIT_POINTS - 50));
    }

    @Test
    public void should_quite_tool_house_after_input_f() throws Exception {
        player.executed("f");

        assertThat(player.getHandler(), is(nullValue()));
    }

    @Test
    public void should_return_empty_handler_with_ten_tools() throws Exception {
        for (int i = 0; i < 10; i ++)
            player.executed("1");

        assertThat(player.getHandler(), is(nullValue()));
    }

    @Test
    public void should_return_empty_handler_without_enough_points() throws Exception {
        player = Player.createPlayerWithPoints(waitToolResponseHandler, location, INIT_BALANCE, 50);

        player.executed("1");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getTools().size(), is(1));
        assertThat(player.getTools().get(0).getType(), is(Tool.ToolType.BLOCK));
        assertThat(player.getPoints(), is(0));
    }
}
