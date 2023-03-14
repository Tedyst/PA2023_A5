package ro.tedyst;

import java.util.Date;

public class Designer extends Person {
    protected int creations;

    public Designer(int id, String name, Date birthDate, int creations){
        super(id, name, birthDate);
        this.creations = creations;
    }

    public int getCreations() {
        return creations;
    }

    public void setCreations(int creations) {
        this.creations = creations;
    }

    @Override
    public String toString() {
        return "Designer{" +
                "creations=" + creations +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
