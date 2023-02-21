package org.example;

public class LatinSquare {
    int[][] array;
    int size;

    public LatinSquare(int n){
        size = n;
        array = new int[n][n];
        for(int i=0; i<n; i++){
            array[0][i] = i+1;
        }
        for(int i=1;i<n;i++)
            for(int j=0;j<n;j++){
                array[i][j] = array[i-1][(n+j-1)%n];
            }
    }

    public void Print(){
        PrintLines();
        System.out.print('\n');
        PrintColumns();
        System.out.print('\n');
    }

    public void PrintLines(){
        for(int i=0; i<size; i++) {
            String s = "";
            for (int j = 0; j < size; j++){
                s += array[i][j];
                s += ' ';
            }
            System.out.print("Line " + i + " : " + s + '\n');
        }
    }

    public void PrintColumns(){
        for(int i=0; i<size; i++) {
            String s = "";
            for (int j = 0; j < size; j++){
                s += array[j][i];
                s += ' ';
            }
            System.out.print("Column " + i + " : " + s + '\n');
        }
        System.out.print('\n');
    }
}
