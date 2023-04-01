package ro.tedyst.game;

import java.util.*;

public class GameAI {
    private Game game;
    private Set<Edge> gameEdges;

    public GameAI(Game game) {
        this.game = game;
        this.gameEdges = new HashSet<>(game.getEdges());
    }

    public List<Edge> getEdgesOfColor(EdgeColor color) {
        List<Edge> edges = new ArrayList<>();
        for (Edge e : game.getEdges()) {
            if (e.getColor() == color) {
                edges.add(e);
            }
        }
        return edges;
    }

    public List<Edge> getAlmostTriangles(Collection<Edge> edges) {
        List<Edge> almostTriangles = new ArrayList<>();
        for (Edge e1 : edges) {
            for (Edge e2 : edges) {
                if (e1 == e2)
                    continue;
                if (e1.getFrom() == e2.getFrom() && gameEdges.contains(new Edge(e1.getTo(), e2.getTo()))) {
                    almostTriangles.add(new Edge(e1.getTo(), e2.getTo()));
                } else if (e1.getFrom() == e2.getTo() && gameEdges.contains(new Edge(e1.getTo(), e2.getFrom()))) {
                    almostTriangles.add(new Edge(e1.getTo(), e2.getFrom()));
                } else if (e1.getTo() == e2.getFrom() && gameEdges.contains(new Edge(e1.getFrom(), e2.getTo()))) {
                    almostTriangles.add(new Edge(e1.getFrom(), e2.getTo()));
                } else if (e1.getTo() == e2.getTo() && gameEdges.contains(new Edge(e1.getFrom(), e2.getFrom()))) {
                    almostTriangles.add(new Edge(e1.getFrom(), e2.getFrom()));
                }
            }
        }
        return almostTriangles;
    }

    public List<Edge> neighboringEdges(List<Edge> edges, EdgeColor color){
        List<Edge> neighboringEdges = new ArrayList<>();
        for(Edge e1 : edges){
            for(Edge e2 : game.getEdges()){
                if(e1 == e2 || edges.contains(e2)) {
                    continue;
                }
                if(e2.getColor() == color && (e1.getFrom() == e2.getFrom() || e1.getFrom() == e2.getTo() || e1.getTo() == e2.getFrom() || e1.getTo() == e2.getTo())){
                    neighboringEdges.add(e2);
                }
            }
        }
        return neighboringEdges;
    }

    public Edge play() {
        if(game.isFinished())
            return null;
        List<Edge> currentPlayerEdges = getEdgesOfColor(game.getState() == GameState.PLAYER_1_TURN ? EdgeColor.BLUE : EdgeColor.RED);
        List<Edge> secondPlayerEdges = getEdgesOfColor(game.getState() == GameState.PLAYER_1_TURN ? EdgeColor.RED : EdgeColor.BLUE);
        // play the edges that will create a triangle
        List<Edge> almostTriangles = getAlmostTriangles(currentPlayerEdges);
        if(almostTriangles.size() > 0)
            return almostTriangles.get((int) (Math.random() * almostTriangles.size()));
        // play the edges that will block the creation of a triangle for the other player
        List<Edge> almostTrianglesOfSecondPlayer = getAlmostTriangles(secondPlayerEdges);
        if(almostTrianglesOfSecondPlayer.size() > 0)
            return almostTrianglesOfSecondPlayer.get((int) (Math.random() * almostTrianglesOfSecondPlayer.size()));
        // play edges that are next to the edges of the current player
        List<Edge> neighboringEdges = neighboringEdges(currentPlayerEdges, EdgeColor.NONE);
        if(neighboringEdges.size() > 0)
            return neighboringEdges.get(0);
        // play a random edge
        return getRandomEdgeToPlay(getEdgesOfColor(EdgeColor.NONE));
    }

    public Edge getRandomEdgeToPlay(List<Edge> edges){
        return edges.get((int) (Math.random() * edges.size()));
    }
}
