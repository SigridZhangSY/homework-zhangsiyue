package rich;

import org.junit.Before;
import org.junit.Test;
import rich.place.Place;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MapTest {
    private Place start;
    private Place target;
    private Place passBy;
    private Map map;

    @Before
    public void setUp() throws Exception {
        start = mock(Place.class);
        target = mock(Place.class);
        passBy = mock(Place.class);
        map = new Map(new ArrayList<Place>(){{
            add(start);
            add(passBy);
            add(target);
        }});
    }

    @Test
    public void should_find_move_destination() throws Exception {
        assertThat(map.move(start, 2), is(target));
        assertThat(map.move(start, 3), is(start));
    }

    @Test
    public void should_get_place_with_start_and_distance() throws Exception {
        assertThat(map.getPlace(start, -1), is(target));
        assertThat(map.getPlace(start, 1), is(passBy));
    }

    @Test
    public void should_get_place_with_position() throws Exception {
        assertThat(map.getPlace(0), is(start));
        assertThat(map.getPlace(2), is(target));
    }
}
