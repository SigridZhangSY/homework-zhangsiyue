package rich;

import org.junit.Test;
import rich.place.Estate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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
}
