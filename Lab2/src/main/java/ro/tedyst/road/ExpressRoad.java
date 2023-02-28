package ro.tedyst.road;

import ro.tedyst.location.Location;

public class ExpressRoad extends Road {
    /**
     * The constructor for ExpressRoad
     * @param name the name of ExpressRoad
     * @param a the first location
     * @param b the second location
     */
    public ExpressRoad(String name, Location a, Location b) {
        super(name, a, b);
    }

    @Override
    public String toString() {
        return "ExpressRoad{" +
                "name='" + getName() + '\'' +
                ", speedLimit=" + getSpeedLimit() +
                ", length=" + getLength() +
                ", a=" + getLocationA() +
                ", b=" + getLocationB() +
                '}';
    }
}
