package ro.tedyst;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Project> projects = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Project("P" + i) )
                .collect(Collectors.toList());
        List<Student> students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i, projects.subList(0, i)) )
                .collect(Collectors.toList());
        TreeSet<Project> projects_set = new TreeSet<>(projects);
        LinkedList<Student> students_list = new LinkedList<>(students);
        students_list.sort(Student::compareTo);

        for(Project project : projects_set)
            System.out.println(project);
        for(Student student : students_list)
            System.out.println(student);

        Problem p = new Problem(students_list, projects);

        System.out.println();

        System.out.println("Outliers: ");
        for(Student s : p.getOutliers())
            System.out.println(s);

        System.out.println();

        Problem p2 = new ProblemGenerator(10, 12).getProblem();
        System.out.println(p2);

        Solution s = new Solution(p2);
        for(Pair<Student, Project> choice : s.compute()){
            System.out.println(choice.getLeft() + " chose " + choice.getRight());
        }

    }
}