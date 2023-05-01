package ro.tedyst.game;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {
    private Node source;
    private Node target;
    private EdgeColor color = EdgeColor.NONE;

    private Edge(){

    }

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Node getSource() {
        return source;
    }

    @Override
    public Node getTarget() {
        return target;
    }

    public void setColor(EdgeColor color) {
        this.color = color;
    }

    public EdgeColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (getSource() != null ? !getSource().equals(edge.getSource()) : edge.getSource() != null) return false;
        if (getTarget() != null ? !getTarget().equals(edge.getTarget()) : edge.getTarget() != null) return false;
        return getColor() == edge.getColor();
    }

    @Override
    public int hashCode() {
        int result = getSource() != null ? getSource().hashCode() : 0;
        result = 31 * result + (getTarget() != null ? getTarget().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", target=" + target +
                ", color=" + color +
                '}';
    }
}
