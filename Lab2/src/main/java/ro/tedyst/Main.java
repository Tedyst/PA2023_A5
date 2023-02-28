package ro.tedyst;

import ro.tedyst.location.Airport;
import ro.tedyst.location.City;
import ro.tedyst.location.Location;
import ro.tedyst.road.HighwayRoad;
import ro.tedyst.road.Road;

public class Main {
    public static void main(String[] args) {
        Location loc1 = new City("loc1", 0, 1, 100);
        Location loc2 = new Airport("loc2", 1, 3);
        Road r = new HighwayRoad("road1", loc1, loc2, 10);

        Problem p = new Problem();
        p.addLocation(loc1);
        p.addLocation(loc1);
        p.addLocation(loc2);
        p.addRoad(r);
        p.addRoad(r);

        System.out.println(p);

        System.out.println(p.canReachLocation(loc1, loc2));

    }
}