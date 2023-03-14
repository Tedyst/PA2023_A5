package ro.tedyst;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @org.junit.jupiter.api.Test
    void compareTo() {
        Person p = new Person(1, "a", new Date());
        Person p2 = new Person(1, "a", new Date());
        assertEquals(0, p.compareTo(p2));
    }

    @org.junit.jupiter.api.Test
    void getName() {
        Person p = new Person(1, "a", new Date());
        assertEquals("a", p.getName());
    }

    @org.junit.jupiter.api.Test
    void getID() {
        Person p = new Person(1, "a", new Date());
        assertEquals(1, p.getID());
    }

    @org.junit.jupiter.api.Test
    void setName() {
        Person p = new Person(1, "a", new Date());
        p.setName("b");
        assertEquals("b", p.getName());
    }


    @org.junit.jupiter.api.Test
    void testToString() {
        Person p = new Person(1, "a", new Date());
        assertEquals("Person{id=1, name='a', birthDate=" + p.birthDate + "}", p.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Person p = new Person(1, "a", new Date());
        Person p2 = new Person(1, "a", new Date());
        assertEquals(p, p2);
    }
}