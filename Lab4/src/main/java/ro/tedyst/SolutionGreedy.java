package ro.tedyst;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionGreedy {
    private Problem problem;

    public SolutionGreedy(Problem p) {
        this.problem = p;
    }

    public Problem getProblem(){
        return problem;
    }

    public List<Pair<Student, Project>> compute() {
        List<Student> students = problem.getStudentList();
        HashMap<Project, Student> selected_projects = new HashMap<>();
        students.sort(Comparator.comparingInt(a -> a.getAdmissibleProjects().size()));
        for(Student student : students) {
            for(Project p : student.getAdmissibleProjects())
                if(!selected_projects.containsKey(p)){
                    selected_projects.put(p, student);
                    break;
                }
        }
        return selected_projects.entrySet().stream()
                .map((a) -> new ImmutablePair<>(a.getValue(), a.getKey()))
                .collect(Collectors.toList());
    }
}
