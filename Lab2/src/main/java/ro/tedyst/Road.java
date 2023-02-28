package ro.tedyst;

import ro.tedyst.locations.Location;

public class Road {
    private String name;
    private RoadType type;
    private float speedLimit, length;

    private Location a, b;

    public Road(String name, RoadType type, Location a, Location b) {
        this.name = name;
        this.type = type;
        this.a = a;
        this.b = b;
        this.length = a.euclideanDistanceToLocation(b);
    }

    public float getLength(){
        return length;
    }

    public void setLength(float length){
        if(length < a.euclideanDistanceToLocation(b))
            return;
        this.length = length;
    }

    public Location getLocationA(){
        return a;
    }

    public void setLocationA(Location a){
        this.a = a;
        this.length = a.euclideanDistanceToLocation(b);
    }

    public Location getLocationB(){
        return b;
    }

    public void setLocationB(Location b){
        this.b = b;
        this.length = a.euclideanDistanceToLocation(b);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public float getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(float speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Road))
            return false;
        Road road = (Road) obj;
        return road.type == type && road.name == name;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", speedLimit=" + speedLimit +
                ", length=" + length +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
