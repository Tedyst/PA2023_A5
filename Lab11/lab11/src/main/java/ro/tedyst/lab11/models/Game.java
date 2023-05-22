package ro.tedyst.lab11.models;

import jakarta.persistence.*;
import ro.tedyst.lab11.exceptions.InvalidGameException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Game {
    public static int MAXIMUM_PLAYERS = 2;
    public static int TIME_PER_PLAYER = 60;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Player> players;

    @ElementCollection()
    private Map<Player, Integer> remainingTime = new HashMap<>();

    @OneToOne(cascade=CascadeType.ALL)
    private Board board = new Board();

    public Integer getRemainingTime(Player player) {
        return remainingTime.get(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) throws InvalidGameException {
        if (players.size() >= MAXIMUM_PLAYERS) {
            throw new InvalidGameException("Game is full");
        }
        players.add(player);
    }

    public void removePlayer(Player player) throws InvalidGameException {
        if (!players.contains(player)) {
            throw new InvalidGameException("Player is not in this game");
        }
        players.remove(player);
    }

    public void start() {
        for (Player player : players) {
            remainingTime.put(player, TIME_PER_PLAYER * 1000);
        }
    }

    public Board getBoard() {
        return board;
    }
}
