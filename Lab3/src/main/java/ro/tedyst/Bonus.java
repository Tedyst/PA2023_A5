package ro.tedyst;

import java.util.*;

public class Bonus {
    final private Network network;

    public Bonus(Network n){
        network = n;
    }

    public Network getNetwork() {
        return network;
    }

    public List<Node> getArticulationPoints(){
        network.sortIdentifiableNodes();

        HashMap<Node, Boolean> visited = new HashMap<>();
        HashMap<Node, Integer> discoveryTime = new HashMap<>();
        HashMap<Node, Integer> earliestTime = new HashMap<>();
        HashMap<Node, Boolean> isArticulation = new HashMap<>();
        for(Node node : getNetwork().getIdentifiableNodes())
            if(!visited.containsKey(node))
                DFS(node, visited, discoveryTime, earliestTime, 0, null, isArticulation);

        List<Node> result = new ArrayList<>();
        isArticulation.forEach((Node n, Boolean b) -> result.add(n));
        return result;
    }

    public List<Node> getConexComponent(Node n){
        List<Node> result = new ArrayList<>();
        HashMap<Node, Boolean> visited = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(n);
        while(!q.isEmpty()){
            Node n2 = q.poll();
            if(!visited.containsKey(n2)){
                visited.put(n2, true);
                result.add(n2);
                if(n2 instanceof Person){
                    Person p = (Person) n2;
                    p.getRelationships().forEach((Node n3, String relation) -> {
                        if(!visited.containsKey(n3))
                            q.add(n3);
                    });
                }
            }
        }
        return result;
    }

    public List<List<Node>> getConexComponents(){
        List<List<Node>> result = new ArrayList<>();
        HashMap<Node, Boolean> visited = new HashMap<>();
        for(Node node : getNetwork().getIdentifiableNodes()){
            if(!visited.containsKey(node)){
                List<Node> component = getConexComponent(node);
                result.add(component);
                component.forEach((Node n) -> visited.put(n, true));
            }
        }
        return result;
    }

    public List<List<Node>> getBlocks(){
        Network n = new Network();
        List<Node> articulationPoints = getArticulationPoints();
        for(Node node : network.getIdentifiableNodes()) {
            if(!articulationPoints.contains(node) && node instanceof Person)
                n.addPerson((Person) node);
        }
        Bonus b = new Bonus(n);
        return b.getConexComponents();
    }

    private void DFS(
        Node n,
        HashMap<Node, Boolean> visited,
        HashMap<Node, Integer> discoveryTime,
        HashMap<Node, Integer> earliestTime,
        int time,
        Node parent,
        HashMap<Node, Boolean> isArticulation
    ){
        final int[] children = {0};
        visited.put(n, true);
        discoveryTime.put(n, time+1);
        earliestTime.put(n, time+1);

        if(n instanceof Person) {
            Person p = (Person) n;
            p.getRelationships().forEach((Node n2, String relation) -> {
                if(!visited.containsKey(n2)) {
                    children[0] += 1;
                    DFS(n2, visited, discoveryTime, earliestTime, time + 1, n, isArticulation);
                    earliestTime.put(n, Math.min(earliestTime.get(n), earliestTime.get(n2)));

                    if (parent != null && earliestTime.get(n2) >= discoveryTime.get(n))
                        isArticulation.put(n, true);
                } else if (n2 != parent){
                    earliestTime.put(n, Math.min(earliestTime.get(n), discoveryTime.get(n2)));
                }
            });

            if(parent == null && children[0] > 1) {
                isArticulation.put(n, true);
            }
        }
    }
}
