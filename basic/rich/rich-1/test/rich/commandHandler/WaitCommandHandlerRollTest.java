package rich.commandHandler;

import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.Map;
import rich.Player;
import rich.place.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitCommandHandlerRollTest {

    private Dice dice;
    private Place start;
    private Map map;
    private Player player;

    final double INIT_BALANCE = 1000;
    final double IN_BALANCE = 200;

    @Before
    public void setUp() throws Exception {
        start  = mock(Place.class);
        map = mock(Map.class);
        dice = mock(Dice.class);
        player = new Player(new WaitCommandHandler(map, dice), start);
        when(dice.next()).thenReturn(1);
    }

    @Test
    public void should_return_wait_response_handler_when_roll_to_empty() throws Exception {
        Estate target  = new Estate(null);
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler() instanceof WaitBuyResponseHandler, is(true));
        assertThat(player.getCurrentPlace(), is(target));
    }

    @Test
    public void should_return_wait_response_handler_when_roll_to_owned_estate() throws Exception {
        Estate target  = new Estate(player);
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler() instanceof WaitBuildResponseHandler, is(true));
        assertThat(player.getCurrentPlace(), is(target));
    }

    @Test
    public void should_return_null_when_roll_to_other_estate() throws Exception {
        Place ownerLocation = mock(Place.class);
        Player otherPlayer = new Player(null, ownerLocation, INIT_BALANCE);
        Estate target  = Estate.createEstateWithOwner(IN_BALANCE, otherPlayer);
        player = new Player(new WaitCommandHandler(map, dice), start, INIT_BALANCE);
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getCurrentPlace(), is(target));
        assertThat(player.getBalance(), is(INIT_BALANCE - target.getPrice()*0.5));
        assertThat(otherPlayer.getBalance(), is(INIT_BALANCE + target.getPrice()*0.5));
    }

    @Test
    public void should_return_wait_gift_response_after_roll_to_gift_house() throws Exception {
        GiftHouse target = new GiftHouse();
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler() instanceof WaitGiftResponseHandler, is(true));
        assertThat(player.getCurrentPlace(), is(target));
    }

    @Test
    public void should_return_wait_tool_response_after_roll_to_tool_house() throws Exception {
        ToolHouse target = new ToolHouse();
        when(map.move(eq(start), eq(1))).thenReturn(target);
        player = Player.createPlayerWithPoints(new WaitCommandHandler(map, dice), start, INIT_BALANCE, 1000);

        player.executed("roll");

        assertThat(player.getHandler() instanceof WaitToolResponseHandler, is(true));
        assertThat(player.getCurrentPlace(), is(target));
    }

    @Test
    public void should_return_empty_handler_when_roll_to_tool_house_with_ten_tools() throws Exception {
        ToolHouse target = new ToolHouse();
        player = Player.createPlayerWithPoints(new WaitCommandHandler(map, dice), start, INIT_BALANCE, 1000);
        for (int i = 0; i < 10; i ++)
            player.buyTool(0);
        assertThat(player.getTools().size(), is(10));
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler(), is(nullValue()));
    }

    @Test
    public void should_return_wait_magic_reponse_when_roll_to_magic_house() throws Exception {
        MagicHouse target = new MagicHouse();
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler() instanceof WaitMagicResponse, is(true));
        assertThat(player.getCurrentPlace(), is(target));

    }

    @Test
    public void should_return_null_when_roll_to_mine() throws Exception {
        Mine target = new Mine();
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getCurrentPlace(), is(target));

    }

    @Test
    public void should_return_null_when_roll_to_hospital() throws Exception {
        Hospital target = new Hospital();
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getCurrentPlace(), is(target));

    }

    @Test
    public void should_return_null_when_roll_to_prison() throws Exception {
        Prison target = new Prison();
        when(map.move(eq(start), eq(1))).thenReturn(target);

        player.executed("roll");

        assertThat(player.getHandler(), is(nullValue()));
        assertThat(player.getCurrentPlace(), is(target));

    }

    @Test
    public void should_stop_when_pass_by_block() throws Exception {
        Place passBy = new Estate(null);
        Place start = mock(Place.class);
        passBy.setBlock();
        map = new Map(new ArrayList<Place>(){{
            add(start);
            add(passBy);
        }});
        when(dice.next()).thenReturn(2);
        player = new Player(new WaitCommandHandler(map, dice), start);

        player.executed("roll");

        assertThat(player.getCurrentPlace(), is(passBy));
        assertThat(passBy.isBlocked(), is(false));
    }

    @Test
    public void should_go_to_hospital_when_pass_by_bomb() throws Exception {
        Place passBy = new Estate(null);
        Hospital hospital = new Hospital();
        Place start = mock(Place.class);
        passBy.setBomb();
        map = new Map(new ArrayList<Place>(){{
            add(start);
            add(passBy);
            add(hospital);
        }});
        when(dice.next()).thenReturn(2);
        player = new Player(new WaitCommandHandler(map, dice), start);

        player.executed("roll");

        assertThat(player.getCurrentPlace(), is(hospital));
        assertThat(player.getWaitTimes(), is(3));
        assertThat(passBy.isBombed(), is(false));
    }
}
