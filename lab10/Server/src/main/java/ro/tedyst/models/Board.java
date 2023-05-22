package ro.tedyst.models;

public class Board {
    final static int BOARD_SIZE = 15;
    private Player[][] board = new Player[BOARD_SIZE][BOARD_SIZE];
    private Player lastPlayer;

    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    public Player[][] getBoard() {
        return board;
    }

    public boolean placePiece(Player player, int x, int y) {
        if(player == lastPlayer)
            return false;
        lastPlayer = player;
        if (board[x][y] == null) {
            board[x][y] = player;
            return true;
        }
        return false;
    }

    public boolean won(Player player) {
        for(int i = 2; i < BOARD_SIZE - 2; i++)
            for(int j = 2; j < BOARD_SIZE - 2; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player && board[i][j - 1] == player && board[i][j - 2] == player)
                    return true;
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player && board[i - 1][j] == player && board[i - 2][j] == player)
                    return true;
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player && board[i - 1][j - 1] == player && board[i - 2][j - 2] == player)
                    return true;
                if (board[i][j] == player && board[i + 1][j - 1] == player && board[i + 2][j - 2] == player && board[i - 1][j + 1] == player && board[i - 2][j + 2] == player)
                    return true;
            }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for(int i = 0; i < BOARD_SIZE; i++)
            sb.append(i).append(" ");
        sb.append("\n");
        for(int i = 0; i < BOARD_SIZE; i++) {
            sb.append(i).append(" ");
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(board[i][j] == null)
                    sb.append("- ");
                else
                    sb.append(board[i][j].getName().charAt(0)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
