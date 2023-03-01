package ro.tedyst;

import ro.tedyst.location.Location;

import java.util.Comparator;

public class SolutionLocationComparator implements Comparator<Location> {
    final private Solution s;

    public SolutionLocationComparator(Solution s) {
        this.s = s;
    }

    public int compare(Location loc1, Location loc2) {
        return s.getDist().get(loc1).compareTo(s.getDist().get(loc2));
    }
}
