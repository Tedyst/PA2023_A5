package ro.tedyst.road;

import ro.tedyst.location.Location;

public class HighwayRoad extends Road {
    int houses;

    /**
     * The constructor for HighwayRoad
     *
     * @param name the name of the HighwayRoad
     * @param a the first location
     * @param b the second location
     * @param houses the number of houses on HighwayRoad
     */
    public HighwayRoad(String name, Location a, Location b, int houses) {
        super(name, a, b);
        this.houses = houses;
    }

    /**
     * Gets the number of houses on HighwayRoad
     *
     * @return the number of houses
     */
    public int getHouses() {
        return houses;
    }

    /**
     * Sets the number of houses on HighwayRoad
     *
     * @param houses the number of houses
     */
    public void setHouses(int houses) {
        this.houses = houses;
    }

    @Override
    public String toString() {
        return "HighwayRoad{" +
                "name='" + getName() + '\'' +
                ", speedLimit=" + getSpeedLimit() +
                ", length=" + getLength() +
                ", a=" + getLocationA() +
                ", b=" + getLocationB() +
                '}';
    }
}
