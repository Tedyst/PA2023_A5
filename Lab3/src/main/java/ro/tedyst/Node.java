package ro.tedyst;

public interface Node extends Comparable<Node> {

    String getName();

    int getID();

    default double getWeight(){
        return 0.0;
    }
}
