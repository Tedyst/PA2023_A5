package ro.tedyst.location;

public class City extends Location {
    int population;

    /**
     * The constructor for City
     *
     * @param name the name of the City
     * @param x the x coordinate of the City
     * @param y the y coordinate of the City
     * @param population the population of the City
     */
    public City(String name, int x, int y, int population) {
        super(name, x, y);
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + getName() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", population=" + getPopulation() +
                '}';
    }
}
