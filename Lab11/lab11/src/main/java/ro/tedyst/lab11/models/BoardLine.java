package ro.tedyst.lab11.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BoardLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Player> columns = new ArrayList<>();

    public BoardLine() {
        for(int i = 0; i < Board.BOARD_SIZE; i++) {
            columns.add(null);
        }
    }

    public Long getId() {
        return id;
    }

    public Player getColumn(int position) {
        return columns.get(position);
    }

    public List<Player> getColumns() {
        return columns;
    }
}
