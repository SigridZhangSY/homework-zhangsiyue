package rich.Place;

import org.junit.Test;
import rich.Player;
import rich.place.Estate;
import rich.place.Hospital;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class EstateTest {

    final double INIT_BALANCE = 1000;
    final double IN_BALANCE = 200;

    @Test
    public void should_pay_fee_when_other_player_come_here() throws Exception {
        Estate ownerLocation = mock(Estate.class);
        Player owner = new Player(null, ownerLocation, INIT_BALANCE);
        Estate estate = Estate.createEstateWithOwner(IN_BALANCE, owner);
        Player player = new Player(null, estate, INIT_BALANCE);

        estate.arrive(player);

        assertThat(player.getBalance(), is(INIT_BALANCE - estate.getPrice() * 0.5));
        assertThat(player.isEndGame(), is(false));
        assertThat(owner.getBalance(), is(INIT_BALANCE + estate.getPrice() * 0.5));
    }

    @Test
    public void should_lose_game_when_other_player_come_here_without_enough_fee() throws Exception {
        Estate ownerLocation = mock(Estate.class);
        Player owner = new Player(null, ownerLocation, INIT_BALANCE);
        Estate estate = Estate.createEstateWithOwner(IN_BALANCE, owner);
        Player player = new Player(null, estate, 0);

        estate.arrive(player);

        assertThat(player.isEndGame(), is(true));
    }

    @Test
    public void should_not_pay_fee_when_owner_is_in_hospital() throws Exception {
        Hospital ownerLocation = mock(Hospital.class);
        Player owner = new Player(null, ownerLocation, INIT_BALANCE);
        Estate estate = Estate.createEstateWithOwner(IN_BALANCE, owner);
        Player player = new Player(null, estate, INIT_BALANCE);

        estate.arrive(player);

        assertThat(player.getBalance(), is(INIT_BALANCE));
        assertThat(player.isEndGame(), is(false));
        assertThat(owner.getBalance(), is(INIT_BALANCE));

    }

    @Test
    public void should_not_pay_fee_with_free_times() throws Exception {
        Estate ownerLocation = mock(Estate.class);
        Player owner = new Player(null, ownerLocation, INIT_BALANCE);
        Estate estate = Estate.createEstateWithOwner(IN_BALANCE, owner);
        Player player = new Player(null, estate, INIT_BALANCE);
        player.selectGift(3);

        estate.arrive(player);

        assertThat(player.getBalance(), is(INIT_BALANCE));
        assertThat(player.isEndGame(), is(false));
        assertThat(owner.getBalance(), is(INIT_BALANCE));

    }
}
