package ro.tedyst;

import java.util.List;

public class GameMap {
    private Cell[][] map;
    private int size;
    private SharedMemory memory;

    public GameMap(int size, SharedMemory memory){
        this.memory = memory;
        this.size = size;
        this.map = new Cell[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                this.map[i][j] = new Cell();
    }

    public int visit(int x, int y, Worker worker){
        synchronized (map[x][y]) {
            if (map[x][y].visited())
                return 0;
            List<Token> tokens = memory.extractMultipleTokens(size);
            map[x][y].addTokens(tokens);
            System.out.println(worker.getName() + " visited cell x=" + x + ", y=" + y + ", and put the tokens " + tokens);
            return tokens.size();
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++)
                sb.append(map[i][j] + " ");
            sb.append('\n');
        }
        return sb.toString();
    }
}
