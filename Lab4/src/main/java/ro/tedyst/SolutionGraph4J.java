package ro.tedyst;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SolutionGraph4J {
    Problem p;
    Graph g;
    HashMap<Object, Integer> map = new HashMap<>();
    ArrayList<Object> reverse_map = new ArrayList<>();
    List<Pair<Student, Project>> result;

    public SolutionGraph4J(Problem p){
        this.p = p;
        int count = 0;
        for(Student s : p.getStudentList()) {
            map.put(s, count);
            reverse_map.add(count, s);
            count++;
        }
        for(Project pr : p.getProjectList()){
            map.put(pr, count);
            reverse_map.add(count, pr);
            count++;
        }
        GraphBuilder gb = GraphBuilder.empty();
        for(Student s : p.getStudentList())
            for(Project pr : p.getProjectList()){
                gb = gb.addEdge(
                    (int)map.get(s),
                    (int)map.get(pr)
                );
            }

        g = gb.buildDigraph();
    }

    public Problem getProblem(){
        return p;
    }

    public SolutionGraph4J compute() {
        result = new ArrayList<>();
        HopcroftKarpMaximumMatching hopcroftKarpMaximumMatching = new HopcroftKarpMaximumMatching(g);
        Matching matching = hopcroftKarpMaximumMatching.getMatching();
        int[][] edges = matching.edges();
        for(int i=0; i < edges.length; i++){
            if(reverse_map.get(edges[i][0]) instanceof Student && reverse_map.get(edges[i][1]) instanceof Project)
                result.add(new ImmutablePair<>(
                    (Student) reverse_map.get(edges[i][0]),
                    (Project) reverse_map.get(edges[i][1])
                ));
        }
        return this;
    }

    public List<Pair<Student, Project>> getResult() {
        return result;
    }

    // Determine a maximum cardinality set of students and projects such that there is no admissible pair (student-project) formed with elements of this set.
    public List<Object> getUnadmissibleSet() {
        List<Object> unadmissibleSet = new ArrayList<>();
        for(Student s : p.getStudentList())
            unadmissibleSet.add(s);
        for(Project pr : p.getProjectList())
            unadmissibleSet.add(pr);
        for(Pair<Student, Project> pair : result){
            unadmissibleSet.remove(pair.getLeft());
            unadmissibleSet.remove(pair.getRight());
        }
        return unadmissibleSet;
    }
}
