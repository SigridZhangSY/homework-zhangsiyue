package rich;

import rich.place.Hospital;
import rich.place.Place;

import java.util.List;

public class Map {
    private List<Place> places;

    public Map(List<Place> places){
        this.places = places;
    }

    public Place move(Place start, int step){
        int current = places.indexOf(start);
        Place target = start;
        for (int i = 1 ; i <= step; i++){
            target = places.get((current + i) % places.size());
            if(target.isBlocked() || target.isBombed())
                return target;
        }
        return target;
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

    public void clearTool(Place start, int distance){
        int currentPosition = places.indexOf(start);
        for (int i = 1; i <= distance; i++)
            places.get((currentPosition + i) % places.size()).clearTool();
    }

    public Place findHospital(){
        return places.stream().filter(place -> place instanceof Hospital).findFirst().get();
    }
}
