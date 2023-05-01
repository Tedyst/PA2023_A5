package ro.tedyst.game;

import java.util.List;

public class GameUtils {
    public static double distanceFromPointToEdge(int x, int y, Edge e){
        int x1 = e.getSource().getX();
        int y1 = e.getSource().getY();
        int x2 = e.getTarget().getX();
        int y2 = e.getTarget().getY();
        double distance = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1) / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        return distance;
    }

    public static Edge getClosestEdge(List<Edge> edges, int x, int y, double maxDistance){
        double minDistance = Double.MAX_VALUE;
        Edge closestEdge = null;
        for(Edge e : edges){
            double distance = distanceFromPointToEdge(x, y, e);
            if(distance < minDistance){
                minDistance = distance;
                closestEdge = e;
            }
        }
        if (minDistance > maxDistance)
            return null;
        return closestEdge;
    }

    public static void runAINextMove(Game game){
        GameAI gameAI = new GameAI(game);
        System.out.println("AI is playing");
        Edge edge = gameAI.play();
        if(edge != null){
            game.clickOnEdge(edge);
            System.out.println("AI played on edge " + edge);
        } else {
            System.out.println("No more moves");
        }
    }
}
