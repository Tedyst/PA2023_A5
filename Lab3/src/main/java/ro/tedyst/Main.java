package ro.tedyst;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Node> nodeList = new ArrayList<>();
        Network n = new Network();
        Person p1 = new Designer(1, "Person 1", new Date(2003, Calendar.APRIL, 14), 1);
        Person p2 = new Programmer(2, "Person 2", new Date(2002, Calendar.SEPTEMBER, 21), "https://github.com/Tedyst");
        Person p3 = new Person(5, "Person 5", new Date(2003, Calendar.APRIL, 14));
        Company c1 = new Company(3, "Company 3");
        Company c2 = new Company(4, "Company 4");
        nodeList.add(p1);
        nodeList.add(p2);
        nodeList.add(c1);
        nodeList.add(c2);
        System.out.println(nodeList);

        p1.addRelationship(p2, "friend");
        p2.addRelationship(c2, "employee");
        p1.addRelationship(c1, "employee");
        n.addPerson(p1);
        n.addPerson(p2);
        n.addPerson(p3);
        System.out.println(n);

        System.out.println();

        Bonus b = new Bonus(n);

        for (Node articulationPoint : b.getArticulationPoints()) {
            System.out.println(articulationPoint + " este un punct de articulatie");
        }

        int count = 0;
        for(List<Node> block : b.getBlocks()){
            System.out.println("Componenta conexa " + count + ":");
            count += 1;
            for(Node node : block)
                System.out.println(node);
        }
    }
}