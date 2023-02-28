package org.example;

public class Bonus2 {
    private boolean array[][];
    private int vertex_degree;
    private int vertices;

    public Bonus2(int vertices, int vertex_degree){
        array = new boolean[vertices][vertices];
        this.vertices = vertices;
        this.vertex_degree = vertex_degree;
    }

    public boolean Construct(){
        int node = 0;
        while(node < vertices){
            while(!IsSaturated(node)){
                if(!AddNode(node)){
                    return false;
                }
            }
            node ++;
        }
        return true;
    }

    public boolean IsSaturated(int node){
        int count = 0;
        for(boolean i : array[node])
            if(i == true)
                count ++;
        return count == vertex_degree;
    }

    public boolean AddNode(int node){
        for(int i=node + 1; i < vertices; i++){
            if(array[i][node] == false && !IsSaturated(i)){
                array[i][node] = array[node][i] = true;
                return true;
            }
        }
        return false;
    }

    public void Print(){
        for(int i=0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++){
                System.out.print(array[i][j] ? '1' : '0');
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}
