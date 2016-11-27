package rich;

import rich.place.Place;

import java.util.List;

public class Map {
    private List<Place> places;

    public Map(List<Place> places){
        this.places = places;
    }

    public Place move(Place start, int step){
        int current = places.indexOf(start);
        return places.get((current + step) % places.size());
    }

    public Place getPlace(Place currentPlace, int distance){
        int currentPosition = places.indexOf(currentPlace);
        int length = places.size();
        int targetPosition = distance + currentPosition;

        targetPosition = targetPosition < 0 ? targetPosition % length + length : targetPosition % length;
        return places.get(targetPosition);
    }

    public Place getPlace(int position){
        return places.get(position);
    }
}
