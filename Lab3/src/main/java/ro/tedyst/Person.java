package ro.tedyst;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Person implements Node {
    private int id;
    protected String name;
    protected Date birthDate;
    protected Map<Node, String> relationships = new HashMap<>();

    public Person(int id, String name, Date birthDate){
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    /**
     * @param person 
     * @return
     */
    @Override
    public int compareTo(Node node) {
        return name.compareTo(node.getName());
    }

    /**
     * @return 
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return 
     */
    @Override
    public int getID() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }

    public void addRelationship(Node n, String type){
        if(relationships.containsKey(n))
            return;
        relationships.put(n, type);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (!Objects.equals(name, person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
