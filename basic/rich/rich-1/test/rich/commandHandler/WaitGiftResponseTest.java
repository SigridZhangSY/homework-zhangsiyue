package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Player;
import rich.place.GiftHouse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class WaitGiftResponseTest {
    private Player player;
    private GiftHouse giftHouse;
    private WaitGiftResponseHandler waitGiftResponseHandler = new WaitGiftResponseHandler();

    final double INIT_BALANCE = 1000;

    @Before
    public void setUp() throws Exception {
        giftHouse = new GiftHouse();
        player = new Player(waitGiftResponseHandler, giftHouse, INIT_BALANCE);
    }

    @Test
    public void should_return_empty_handler_after_select_gift() throws Exception {
        player.executed("1");

        assertThat(player.getBalance(), is(INIT_BALANCE + 2000));
        assertThat(player.getHandler(), is(nullValue()));

        player = new Player(waitGiftResponseHandler, giftHouse, INIT_BALANCE);

        player.executed("2");

        assertThat(player.getPoints(), is(200));
        assertThat(player.getHandler(), is(nullValue()));

        player = new Player(waitGiftResponseHandler, giftHouse, INIT_BALANCE);

        player.executed("3");

        assertThat(player.getFreeTimes(), is(5));
        assertThat(player.getHandler(), is(nullValue()));
    }
}
