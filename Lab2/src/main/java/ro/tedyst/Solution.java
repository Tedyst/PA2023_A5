package ro.tedyst;

import ro.tedyst.location.Location;

import java.util.*;

public class Solution {
    final private List<Location> route = new LinkedList<>();
    final private Map<Location, Float> dist = new HashMap<>();
    final private Map<Location, Boolean> visited = new HashMap<>();
    final private Map<Location, Location> prev = new HashMap<>();
    final private PriorityQueue<Location> queue;
    private float totalDist = 0;
    Problem p;

    public Solution(Problem p) {
        this.p = p;
        this.queue = new PriorityQueue<>(5, new SolutionLocationComparator(this));
    }

    private boolean prepareDijkstraAlgorithm() {
        if (p == null)
            return false;
        if (!p.isValid())
            return false;
        for (Location location : p.getLocations()) {
            dist.put(location, Float.MAX_VALUE);
            prev.put(location, null);
            visited.put(location, false);
        }
        dist.put(p.getStart(), 0f);
        for (Location location : p.getLocations()) {
            queue.add(location);
        }
        return true;
    }

    public void computeDijkstraAlgorithm() {
        if (!prepareDijkstraAlgorithm())
            return;
        while (!queue.isEmpty()) {
            Location loc = queue.remove();
            visited.put(loc, true);

            for (Location neighbor : p.neighbouringLocations(loc))
                if (!visited.get(neighbor)) {
                    float alt = dist.get(loc) + p.getRoadBetweenTwoLocations(loc, neighbor).getLength();
                    if (alt < dist.get(neighbor)) {
                        dist.put(neighbor, alt);
                        prev.put(neighbor, loc);
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
        }
        computeSolution();
    }

    private void computeSolution() {
        Location loc = p.getEnd();
        totalDist = 0;
        while (prev.get(loc) != null) {
            route.add(0, loc);
            totalDist += p.getRoadBetweenTwoLocations(loc, prev.get(loc)).getLength();
            loc = prev.get(loc);
        }

        route.add(0, p.getStart());
    }

    public float getTotalDist() {
        return totalDist;
    }

    public Map<Location, Float> getDist() {
        return dist;
    }

    public List<Location> getRoute() {
        return route;
    }


}
