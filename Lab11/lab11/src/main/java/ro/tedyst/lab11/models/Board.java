package ro.tedyst.lab11.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Board implements Serializable {
    public static int BOARD_SIZE = 15;

    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = new int[BOARD_SIZE];
        }
    }

    public int getPosition(int x, int y) {
        return board[x][y];
    }

    public void setPosition(int x, int y, int value) {
        board[x][y] = value;
    }

    public int[] getLine(int x){
        return board[x];
    }
}
