package ro.tedyst.location;

public class GasStation extends Location {
    public GasStation(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "name='" + getName() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                '}';
    }
}
