package ro.tedyst;

import ro.tedyst.locations.Location;

public class Main {
    public static void main(String[] args) {
        Location loc1 = new Location("loc1", LocationType.CITY, 0, 1);
        Location loc2 = new Location("loc2", LocationType.AIRPORT, 1, 3);
        Road r = new Road("road1", RoadType.HIGHWAY, loc1, loc2);

        Problem p = new Problem();
        p.addLocation(loc1);
        p.addLocation(loc1);
        p.addLocation(loc2);
        p.addRoad(r);
        p.addRoad(r);

        System.out.println(p);

    }
}