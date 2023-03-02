package ro.tedyst;

import ro.tedyst.location.Airport;
import ro.tedyst.location.City;
import ro.tedyst.location.GasStation;
import ro.tedyst.location.Location;
import ro.tedyst.road.ExpressRoad;
import ro.tedyst.road.HighwayRoad;
import ro.tedyst.road.Road;

import java.util.Random;

public class ProblemGenerator {
    private final int MAX_COORDS = 100;
    private Problem problem = new Problem();
    private int cityCount = 0;
    private int roadCount = 0;
    private int generatorCount = 0;

    public int getCityCount() {
        return cityCount;
    }

    public ProblemGenerator setCityCount(int cityCount) {
        this.cityCount = cityCount;
        return this;
    }

    public int getRoadCount() {
        return roadCount;
    }

    public ProblemGenerator setRoadCount(int roadCount) {
        this.roadCount = roadCount;
        return this;
    }

    private void generateLocations() {
        Random rand = new Random();
        for (int locationGenerated = 0; locationGenerated <= cityCount; locationGenerated++) {
            Location loc;
            switch (rand.nextInt(3)) {
                case 1:
                    loc = new Airport("location" + locationGenerated, rand.nextInt(MAX_COORDS), rand.nextInt(MAX_COORDS));
                    break;
                case 2:
                    loc = new City("location" + locationGenerated, rand.nextInt(MAX_COORDS), rand.nextInt(MAX_COORDS), rand.nextInt(MAX_COORDS));
                    break;
                default:
                    loc = new GasStation("location" + locationGenerated, rand.nextInt(MAX_COORDS), rand.nextInt(MAX_COORDS));
                    break;
            }
            problem.addLocation(loc);
        }
        int id1 = rand.nextInt(problem.getLocations().size());
        problem.setStart(
                problem.getLocations().get(id1)
        );
        int id2 = rand.nextInt(problem.getLocations().size());
        while (id1 == id2)
            id2 = rand.nextInt(problem.getLocations().size());
        problem.setEnd(
                problem.getLocations().get(id2)
        );
    }

    private void generateRoads() {
        Random rand = new Random();
        for (int roadGenerated = 0;
             roadGenerated <= roadCount && roadGenerated <= cityCount * (cityCount - 1);
             roadGenerated++
        ) {
            Road road;
            int loc1_id = rand.nextInt(problem.getLocations().size());
            int loc2_id = rand.nextInt(problem.getLocations().size());
            while (loc1_id == loc2_id && problem.getLocations().size() != 1)
                loc2_id = rand.nextInt(problem.getLocations().size());
            Location loc1 = problem.getLocations().get(loc1_id);
            Location loc2 = problem.getLocations().get(loc2_id);
            if (problem.getRoadBetweenTwoLocations(loc1, loc2) != null) {
                roadGenerated--;
                continue;
            }
            switch (rand.nextInt(2)) {
                case 1:
                    road = new ExpressRoad(
                            "road" + roadGenerated,
                            problem.getLocations().get(loc1_id),
                            problem.getLocations().get(loc2_id)
                    );
                    break;
                default:
                    road = new HighwayRoad(
                            "road" + roadGenerated,
                            problem.getLocations().get(loc1_id),
                            problem.getLocations().get(loc2_id),
                            100
                    );
                    break;
            }
            problem.addRoad(road);
        }
    }

    public ProblemGenerator generateProblem() {
        if (roadCount == 0 || cityCount == 0)
            return this;
        generateLocations();
        generateRoads();

        if (!problem.isValid()) {
            System.out.println("new try");
            problem = new Problem();
            generateProblem();
        }

        return this;
    }

    public Problem getProblem() {
        return problem;
    }
}
