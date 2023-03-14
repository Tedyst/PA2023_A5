package ro.tedyst;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BonusTest {

    void prepare(){

    }

    @Test
    void getArticulationPoints() {
        Network n = new Network();
        Person p1 = new Designer(1, "Person 1", new Date(2003, Calendar.APRIL, 14), 1);
        Person p2 = new Programmer(2, "Person 2", new Date(2002, Calendar.SEPTEMBER, 21), "https://github.com/Tedyst");
        Person p3 = new Person(5, "Person 5", new Date(2003, Calendar.APRIL, 14));
        Company c1 = new Company(3, "Company 3");
        Company c2 = new Company(4, "Company 4");

        p1.addRelationship(p2, "friend");
        p2.addRelationship(c2, "employee");
        p1.addRelationship(c1, "employee");
        n.addPerson(p1);
        n.addPerson(p2);
        n.addPerson(p3);

        Bonus b = new Bonus(n);

        assertEquals(2, b.getArticulationPoints().size());
        assertTrue(b.getArticulationPoints().contains(p1) && b.getArticulationPoints().contains(p2));
        assertEquals(5, b.getNetwork().getIdentifiableNodes().size());
    }


    @Test
    void getBlocks() {
        Network n = new Network();
        Person p1 = new Designer(1, "Person 1", new Date(2003, Calendar.APRIL, 14), 1);
        Person p2 = new Programmer(2, "Person 2", new Date(2002, Calendar.SEPTEMBER, 21), "https://github.com/Tedyst");
        Person p3 = new Person(5, "Person 5", new Date(2003, Calendar.APRIL, 14));
        Person p4 = new Person(5, "Person 6", new Date(2003, Calendar.APRIL, 14));
        Company c1 = new Company(3, "Company 3");
        Company c2 = new Company(4, "Company 4");

        p1.addRelationship(p2, "friend");
        p2.addRelationship(c2, "employee");
        p1.addRelationship(c1, "employee");
        p1.addRelationship(p3, "friend");
        p3.addRelationship(p2, "friend");
        n.addPerson(p1);
        n.addPerson(p2);
        n.addPerson(p3);
        n.addPerson(p4);

        Bonus b = new Bonus(n);
        List<List<Node>> result = b.getBlocks();
        assertEquals(3, result.size());
        assertEquals(2, result.get(0).size());
        assertEquals(3, result.get(1).size());
        assertEquals(1, result.get(2).size());
    }
}