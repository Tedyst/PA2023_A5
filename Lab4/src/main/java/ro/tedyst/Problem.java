package ro.tedyst;

import java.util.List;
import java.util.stream.Collectors;

public class Problem {
    private List<Student> studentList;
    private List<Project> projectList;

    public Problem(List<Student> studentList, List<Project> projectList){
        this.projectList = projectList;
        this.studentList = studentList;
    }

    public List<Student> getOutliers() {
        double average = studentList.stream()
                .map(x -> x.getAdmissibleProjects().size()).collect(Collectors.averagingInt(Integer::intValue))
                .doubleValue();
        return studentList.stream().filter(x -> x.admissibleProjects.size() < average).collect(Collectors.toList());
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "studentList=" + studentList +
                ", projectList=" + projectList +
                '}';
    }
}
