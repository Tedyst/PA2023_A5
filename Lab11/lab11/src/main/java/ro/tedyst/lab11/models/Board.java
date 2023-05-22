package ro.tedyst.lab11.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    public static int BOARD_SIZE = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    private final List<BoardLine> lines = new ArrayList<>();

    public Board() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            lines.add(new BoardLine());
        }
    }

    public Long getId() {
        return id;
    }

    public List<BoardLine> getLines() {
        return lines;
    }

    public BoardLine getLine(int position) {
        return lines.get(position);
    }

    public Player getPosition(int x, int y) {
        var line = getLine(x);
        if (line == null) {
            return null;
        }
        return line.getColumn(y);
    }
}
