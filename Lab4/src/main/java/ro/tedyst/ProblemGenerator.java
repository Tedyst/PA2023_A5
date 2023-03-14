package ro.tedyst;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProblemGenerator {
    private Problem p;
    private Faker f = new Faker();
    private Random r = new Random();

    public ProblemGenerator(int students_count,
                            int projects_count){
        List<Project> projects = IntStream.rangeClosed(1, projects_count)
                .mapToObj(i -> new Project(f.programmingLanguage().name()) )
                .collect(Collectors.toList());
        List<Student> students = IntStream.rangeClosed(1, students_count)
                .mapToObj(i -> new Student(f.name().fullName(), null) )
                .collect(Collectors.toList());

        students.stream().forEach(s -> {
            int pj = r.nextInt(projects_count);
            for(int i = 0 ; i < pj; i++)
                s.addAdmissibleProject(projects.get(r.nextInt(projects_count)));
        });

        p = new Problem(students, projects);
    }

    public Problem getProblem() {
        return p;
    }
}
