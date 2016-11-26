package rich;

import rich.place.Place;

public interface Map {
    Place move(Place start, int step);

    Place getPlace(int position);
}
