package ro.tedyst;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    protected List<Project> admissibleProjects;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getAdmissibleProjects() {
        return admissibleProjects;
    }

    public void addAdmissibleProject(Project p){
        if(!admissibleProjects.contains(p))
            admissibleProjects.add(p);
    }

    public void removeAdmissibleProject(Project p){
        if(admissibleProjects.contains(p))
            admissibleProjects.remove(p);
    }

    public Student(String name, List<Project> admissibleProjects){
        this.name = name;
        if(admissibleProjects != null)
            this.admissibleProjects = admissibleProjects;
        else
            this.admissibleProjects = new ArrayList<>();
    }

    @Override
    public int compareTo(Student student) {
        return name.compareTo(student.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "admissibleProjects=" + admissibleProjects +
                ", name='" + name + '\'' +
                '}';
    }
}
