package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Player;
import rich.place.Estate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class WaitBuyResponseHandlerTest {

    private Player player;
    private Estate empty;
    private WaitBuyResponseHandler waitBuyResponseHandler = new WaitBuyResponseHandler();

    final double INIT_BALANCE = 1000;
    final double IN_BALANCE = 200;

    @Before
    public void setUp() throws Exception {
        empty = new Estate(IN_BALANCE);
        player = new Player(waitBuyResponseHandler, empty, INIT_BALANCE);
    }

    @Test
    public void should_return_empty_handler_after_buy_empty() throws Exception {
        player.executed("y");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getBalance(), is(INIT_BALANCE - IN_BALANCE));
    }

    @Test
    public void should_return_empty_handler_after_say_no_to_buy_empty() throws Exception {
        player.executed("n");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getBalance(), is(INIT_BALANCE));
    }
}
