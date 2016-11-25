package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Player;
import rich.place.Estate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class WaitBuildResponseHandlerTest {
    private Player player;
    private Estate estate;
    private WaitBuildResponseHandler waitBuildResponseHandler = new WaitBuildResponseHandler();

    final double INIT_BALANCE = 1000;
    final double IN_BALANCE = 200;

    @Before
    public void setUp() throws Exception {
        estate = new Estate(IN_BALANCE);
        player = new Player(waitBuildResponseHandler, estate, INIT_BALANCE);
    }

    @Test
    public void should_return_empty_handler_after_build_estate() throws Exception {
        player.executed("y");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getBalance(), is(INIT_BALANCE - IN_BALANCE));
        assertThat(estate.getLevel(), is(Estate.LEVEL.ONE));
    }

    @Test
    public void should_return_empty_handler_after_say_no_to_build_estate() throws Exception {
        player.executed("n");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getBalance(), is(INIT_BALANCE));
        assertThat(estate.getLevel(), is(Estate.LEVEL.ZERO));
    }
}
