package ro.tedyst.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Game {
    GameState state;

    List<Node> nodes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    private Game(){

    }

    public Game(int H, int W, int nodes, double edgeProbability) {
        state = GameState.PLAYER_1_TURN;
        int x0 = W / 2; int y0 = H / 2;
        int radius = H / 2 - 10;
        double alpha = 2 * Math.PI / nodes;
        for (int i = 0; i < nodes; i++) {
            Node n = new Node(
                x0 + (int) (radius * Math.cos(alpha * i)),
                y0 + (int) (radius * Math.sin(alpha * i))
            );
            this.nodes.add(n);
        }
        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                if (Math.random() < edgeProbability) {
                    this.edges.add(new Edge(this.nodes.get(i), this.nodes.get(j)));
                }
            }
        }
    }

    public GameState getState() {
        return state;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void clickOnEdge(Edge e){
        if(isFinished() || e == null || e.getColor() != EdgeColor.NONE)
            return;
        if(state == GameState.PLAYER_1_TURN){
            e.setColor(EdgeColor.BLUE);
        }
        else {
            e.setColor(EdgeColor.RED);
        }
        state = nextState();
    }

    public GameState nextState() {
        if(checkForTriangles()){
            if(state == GameState.PLAYER_1_TURN)
                return GameState.PLAYER_1_WIN;
            else
                return GameState.PLAYER_2_WIN;
        }
        for(Edge e : edges) {
            if (e.getColor() == EdgeColor.NONE) {
                if (state == GameState.PLAYER_1_TURN)
                    return GameState.PLAYER_2_TURN;
                else
                    return GameState.PLAYER_1_TURN;
            }
        }
        return GameState.DRAW;
    }

    public boolean checkForTriangles(){
        return false;
    }

    @JsonIgnore
    public boolean isFinished(){
        return state != GameState.PLAYER_1_TURN && state != GameState.PLAYER_2_TURN;
    }
}
