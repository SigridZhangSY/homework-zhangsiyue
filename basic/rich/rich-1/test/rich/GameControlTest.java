package rich;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameControlTest {
    @Test
    public void should_set_init_balance_and_change_game_state() throws Exception {
        GameControl game = new GameControl();

        game.setInitBalance(2000);
        assertThat(game.getInitBalance(), is(2000.0));
        assertThat(game.getState(), is(GameControl.GameState.WAIT_INIT_PLAYER));
    }

    @Test
    public void should_not_set_init_balance_with_invalid_parameter() throws Exception {
        GameControl game = new GameControl();

        game.setInitBalance(100);
        assertThat(game.getInitBalance(), is(10000.0));
        assertThat(game.getState(), is(GameControl.GameState.WAIT_INIT_BALANCE));
    }
}
