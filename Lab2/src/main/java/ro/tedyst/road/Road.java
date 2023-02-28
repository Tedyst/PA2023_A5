package ro.tedyst.road;

import ro.tedyst.RoadType;
import ro.tedyst.location.Location;

public abstract class Road {
    private String name;
    private float speedLimit, length;

    private Location a, b;

    /**
     * The constructor for the Road.
     *
     * @param name The name of the road
     * @param a The first location
     * @param b The second location
     */
    public Road(String name, Location a, Location b) {
        this.name = name;
        this.a = a;
        this.b = b;
        if(a != null)
            this.length = a.euclideanDistanceToLocation(b);
        else
            this.length = Float.MAX_VALUE;
    }

    /**
     * Gets the length of the Road
     *
     * @return the lenth of the Road
     */
    public float getLength(){
        return length;
    }

    /**
     * Sets the length of the road. Cannot be smaller than the euclidian distance between a and b
     *
     * @param length The length to be set
     */
    public void setLength(float length){
        if(length < a.euclideanDistanceToLocation(b))
            return;
        this.length = length;
    }

    /**
     * Gets the first location
     *
     * @return the first location
     */
    public Location getLocationA(){
        return a;
    }

    /**
     * Sets the first location
     *
     * @param a the first location
     */
    public void setLocationA(Location a){
        this.a = a;
        this.length = a.euclideanDistanceToLocation(b);
    }

    /**
     * Gets the second location
     *
     * @return the second location
     */
    public Location getLocationB(){
        return b;
    }

    /**
     * Sets the second location
     *
     * @param b The second location
     */
    public void setLocationB(Location b){
        this.b = b;
        this.length = a.euclideanDistanceToLocation(b);
    }

    /**
     * Gets the name of the Road
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Road
     *
     * @param name the name of the Road
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Speed Limit for the Road
     *
     * @return the speed limit
     */
    public float getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Sets the Speed Limit for the Road
     *
     * @param speedLimit the speed limit to be set
     */
    public void setSpeedLimit(float speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Road))
            return false;
        Road road = (Road) obj;
        return road.name == name;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", speedLimit=" + speedLimit +
                ", length=" + length +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
