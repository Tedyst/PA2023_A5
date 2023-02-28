package ro.tedyst.location;

import java.util.Objects;

public abstract class Location {
    private String name;
    private int x, y;

    /**
     * The constructor for Location
     * @param name the name of the location
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the euclidean distance between this instance and b
     *
     * @param b the second location
     * @return the euclidean distance between a and b
     */
    public float euclideanDistanceToLocation(Location b){
        if(b == null)
            return Float.MAX_VALUE;
        return (float)Math.abs(x - b.getX()) * (float)Math.abs(y - b.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;
        if (!Objects.equals(name, location.name)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
