package ro.tedyst.location;

public class Airport extends Location {
    public Airport(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + getName() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                '}';
    }
}
