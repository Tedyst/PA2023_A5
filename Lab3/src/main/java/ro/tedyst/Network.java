package ro.tedyst;

import java.util.*;

public class Network {
    List<Node> identifiableNodes = new ArrayList<>();

    public void sortIdentifiableNodes(){
        identifiableNodes.sort((Node a, Node b) -> {
            return getImportance(b) - getImportance(a);
        });
    }

    public void addPerson(Person person){
        if(!identifiableNodes.contains(person))
            identifiableNodes.add(person);
        for(Map.Entry<Node, String> neighbor : person.getRelationships().entrySet()){
            if(!identifiableNodes.contains(neighbor.getKey())){
                identifiableNodes.add(neighbor.getKey());
            }
        }
        sortIdentifiableNodes();
    }

    public int getImportance(Node n){
        int result = 0;
        if(n instanceof Person){
            Person p = (Person) n;
            result += p.getRelationships().size();
        }
        for(Node node : identifiableNodes){
            if(node instanceof Person){
                Person p = (Person) node;
                if(p.getRelationships().containsKey(n))
                    result += 1;
            }
        }
        return result;
    }

    public List<Node> getIdentifiableNodes() {
        return identifiableNodes;
    }

    @Override
    public String toString() {
        return "Network{" +
                "identifiableNodes=" + identifiableNodes +
                '}';
    }
}
