package rich;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerCommandTest {
    @Test
    public void should_return_available_commands() throws Exception {
        CommandHandler handler = mock(CommandHandler.class);
        Player player = new Player(handler);
        when(handler.execute(eq("roll"), eq(player))).thenReturn(Optional.of(handler));

        player.executed("roll");

        assertThat(player.getHandler(), is(handler));
    }
}
