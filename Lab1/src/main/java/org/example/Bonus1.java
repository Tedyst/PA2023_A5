package org.example;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Bonus1 {
    int[][] array;
    int size;

    public Bonus1(int n){
        array = new int[n][n];
        size = n;

        for(int i=0;i<n-1;i++) {
            array[i][i + 1] = 1;
            array[i + 1][i] = 1;
        }

        array[0][n-1] = 1;
        array[n-1][0] = 1;
    }

    public void Multiply(int nr){
        int[][] new_array = array.clone();
        for(int count=0; count < nr; count++) {
            int[][] result = new int[size][size];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++){
                    for (int k = 0; k < size; k++)
                        result[i][j] += new_array[i][k] * array[k][j];
                }

            new_array = result;
        }


        array = new_array;
    }

    public void Print(){
        for(int i=0; i<size; i++) {
            for (int j = 0; j < size; j++){
                System.out.print(array[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}
