package ro.tedyst.game;

public class Edge {
    private Node from;
    private Node to;
    private EdgeColor color = EdgeColor.NONE;

    private Edge(){

    }

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
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

        if (getFrom() != null ? !getFrom().equals(edge.getFrom()) : edge.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(edge.getTo()) : edge.getTo() != null) return false;
        return getColor() == edge.getColor();
    }

    @Override
    public int hashCode() {
        int result = getFrom() != null ? getFrom().hashCode() : 0;
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", color=" + color +
                '}';
    }
}
