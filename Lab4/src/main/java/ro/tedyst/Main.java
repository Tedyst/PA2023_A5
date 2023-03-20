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

        Problem p2 = new ProblemGenerator(1000, 1000).getProblem();
        System.out.println(p2);

        System.out.println();
        System.out.println("Greedy Solution:");

        long s1_start = System.currentTimeMillis();
        SolutionGreedy s1 = new SolutionGreedy(p2);
        var s1_sol = s1.compute();
        for(Pair<Student, Project> choice : s1_sol){
            System.out.println(choice.getLeft() + " chose " + choice.getRight());
        }
        long s1_end = System.currentTimeMillis();

        System.out.println();
        System.out.println("Graph4j Solution:");

        long s2_start = System.currentTimeMillis();
        SolutionGraph4J s2 = new SolutionGraph4J(p2);
        var s2_sol = s2.compute().getResult();
        for(Pair<Student, Project> choice : s2_sol){
            System.out.println(choice.getLeft() + " chose " + choice.getRight());
        }
        long s2_end = System.currentTimeMillis();

        System.out.println("Greedy chose " + s1_sol.size());
        System.out.println("Greedy took " + (s1_end - s1_start) + " miliseconds");
        System.out.println("Graph4j chose " + s2_sol.size());
        System.out.println("Graph4j took " + (s2_end - s2_start) + " miliseconds");

        System.out.println("Unadmissible set:");
        for(Object s : s2.getUnadmissibleSet())
            System.out.println(s);
    }
}