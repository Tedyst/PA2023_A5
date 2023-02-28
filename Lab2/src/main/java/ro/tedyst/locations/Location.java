package ro.tedyst.locations;

import ro.tedyst.LocationType;

import java.util.Objects;

public class Location {
    private String name;
    private LocationType type;
    private int x, y;

    public Location(String name, LocationType type, int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

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

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
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
        return type == location.type;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
