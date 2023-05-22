package ro.tedyst.models;

import ro.tedyst.Server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private static int gameCount = 0;
    private static int TIME_PER_PLAYER = 1000000000;
    private final int id;
    private final Board board = new Board();
    private List<Player> players;
    private final Map<Player, Integer> remainingTime = new HashMap<>();
    private boolean started = false;
    private Instant lastActionTime;

    public Game(List<Player> players) {
        this.id = gameCount;
        gameCount++;
        this.players = players;
        lastActionTime = Instant.now();
        for (Player player : players)
            remainingTime.put(player, TIME_PER_PLAYER);
    }

    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
        remainingTime.put(player, TIME_PER_PLAYER);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        remainingTime.remove(player);
    }

    public boolean isStarted() {
        return started;
    }

    public Integer getRemainingTime(Player player) {
        return remainingTime.get(player);
    }

    public boolean removeTimeFromPlayer(Player player) {
        if (remainingTime.get(player) == null)
            return false;
        int timeSinceLastAction = (int) (Instant.now().toEpochMilli() - lastActionTime.toEpochMilli());
        remainingTime.put(player, remainingTime.get(player) - timeSinceLastAction);
        if (remainingTime.get(player) <= 0) {
            return false;
        }
        return true;
    }
}
