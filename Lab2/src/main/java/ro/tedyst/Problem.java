package ro.tedyst;

import ro.tedyst.location.Location;
import ro.tedyst.road.Road;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Problem {
    final private ArrayList<Location> locations;
    final private ArrayList<Road> roads;
    private Location start, end;

    /**
     * Creates an instance of the Problem.
     */
    public Problem(){
        locations = new ArrayList<>();
        roads = new ArrayList<>();
    }

    /**
     * Tries to add the road to the instance of the Problem.
     *
     * @param road The road that will be added if it is valid
     */
    public void addRoad(Road road){
        if(!locations.contains(road.getLocationA()))
            return;
        if(!locations.contains(road.getLocationB()))
            return;
        if(roads.contains(road))
            return;
        roads.add(road);
    }

    /**
     * Calculates the distance between two locations in the current instance of the Problem.
     *
     * @param a The start location
     * @param b The end location
     */
    public boolean canReachLocation(Location a, Location b) {
        Queue<Location> q = new LinkedList<>();
        ArrayList<Location> visited = new ArrayList<>();
        q.add(a);
        while(!q.isEmpty()){
            Location l = q.remove();
            if(visited.contains(l))
                continue;
            visited.add(l);
            if(l == b){
                return true;
            }
            for(Location neighbor : neighbouringLocations(l))
                if(!visited.contains(neighbor))
                    q.add(neighbor);
        }
        return false;
    }

    /**
     * Validates the current instance of the problem
     *
     * @return true if the instance is valid
     */
    public boolean isValid(){
        return !locations.isEmpty() && !roads.isEmpty() && start != null && end != null;
    }

    /**
     * Creates a list of the neighbouring locations to a location loc
     *
     * @param loc The starting location
     * @return a list of locations that are neighbouring loc
     */
    public ArrayList<Location> neighbouringLocations(Location loc){
        ArrayList<Location> neighbours = new ArrayList<>();
        for(Road r : roads){
            if(loc == r.getLocationA())
                neighbours.add(r.getLocationB());
            else if(loc == r.getLocationB())
                neighbours.add(r.getLocationA());
        }
        return neighbours;
    }

    /**
     * Tries to add the location to the instance of the Problem.
     *
     * @param location The location that will be added if it is valid
     */
    public void addLocation(Location location){
        if(locations.contains(location))
            return;
        locations.add(location);
    }

    /**
     * Sets the start location for the Problem.
     *
     * @param a The starting location
     */
    public void setStart(Location a){
        if(locations.contains(a))
            this.start = a;
    }

    /**
     * Sets the end location for the Problem.
     *
     * @param a The end location
     */
    public void setEnd(Location a){
        if(locations.contains(a))
            this.end = a;
    }

    /**
     * Gets the start location for the Problem.
     *
     * @return The starting location
     */
    public Location getStart() {
        return start;
    }

    /**
     * Gets the end location for the Problem.
     *
     * @return The end location
     */
    public Location getEnd() {
        return end;
    }

    /**
     * Gets the roads for the Problem.
     *
     * @return A list of roads
     */
    public ArrayList<Road> getRoads() {
        return roads;
    }

    /**
     * Gets the locations for the Problem.
     *
     * @return A list of locations
     */
    public ArrayList<Location> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "location=" + locations +
                ", road=" + roads +
                '}';
    }
}
