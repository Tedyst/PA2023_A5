package ro.tedyst;

public class Main {
    public static void main(String[] args) {
//        Location loc1 = new City("loc1", 0, 1, 100);
//        Location loc2 = new Airport("loc2", 1, 3);
//        Location loc3 = new City("loc3", 2, 3, 10);
//        Road r = new HighwayRoad("road1", loc1, loc2, 10);
//        Road r2 = new ExpressRoad("road2", loc2, loc3);
//
//        r.setLength(10);
//
//        Problem p = new Problem();
//        p.addLocation(loc1);
//        p.addLocation(loc1);
//        p.addLocation(loc2);
//        p.addLocation(loc3);
//        p.addRoad(r);
//        p.addRoad(r);
//        p.addRoad(r2);
//        p.setStart(loc1);
//        p.setEnd(loc3);
//
//        Solution s = new Solution(p);
//        s.computeDijkstraAlgorithm();
//        System.out.println("The result length is " + s.getTotalDist());
//        int index = 0;
//        for (Location loc : s.getRoute()) {
//            System.out.println((index++) + ". " + loc);
//        }

        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();
        Problem p1 = new ProblemGenerator().setCityCount(10).setRoadCount(30).generateProblem().getProblem();
        Solution s1 = new Solution(p1);
        s1.computeDijkstraAlgorithm();
        System.out.println("Problem generated has a solution of length " + s1.getTotalDist());
        if (s1.getTotalDist() == 0) {
            System.out.println(s1);
        }
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;

        System.out.println("Problem generation and solution took " + runningTime + " miliseconds " +
                "and " + memoryIncrease + " bytes");

    }
}