package ro.tedyst;

import ro.tedyst.locations.Location;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Problem {
    final private ArrayList<Location> locations;
    final private ArrayList<Road> roads;
    private Location start, end;

    public Problem(){
        locations = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void addRoad(Road road){
        if(!locations.contains(road.getLocationA()))
            return;
        if(!locations.contains(road.getLocationB()))
            return;
        if(roads.contains(road))
            return;
        roads.add(road);
    }

    public boolean canReachLocation(Location a, Location b) {
        Queue<Location> q = new LinkedList<>();
        ArrayList<Location> visited = new ArrayList<>();
        q.add(a);
        visited.add(a);
        while(!q.isEmpty()){
            Location l = q.remove();
            if(visited.contains(l))
                continue;
            if(l == b){
                return true;
            }
            for(Location neighbor : neighbouringLocations(l))
                if(!visited.contains(neighbor))
                    q.add(neighbor);
        }
        return false;
    }

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

    public void addLocation(Location location){
        if(locations.contains(location))
            return;
        locations.add(location);
    }

    public void setStart(Location a){
        if(locations.contains(a))
            this.start = a;
    }

    public void setEnd(Location a){
        if(locations.contains(a))
            this.end = a;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }


    public ArrayList<Road> getRoads() {
        return roads;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "locations=" + locations +
                ", roads=" + roads +
                '}';
    }
}
