package ro.tedyst;

import ro.tedyst.models.Game;
import ro.tedyst.models.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private Map<Socket, Player> socketPlayerMap = new HashMap<>();
    private Map<Player, Game> playerGameMap = new HashMap<>();
    private List<Game> games = new ArrayList<>();

    public List<Game> getGames() {
        return games;
    }

    public Player getPlayer(Socket socket) {
        if(socketPlayerMap.containsKey(socket)) {
            return socketPlayerMap.get(socket);
        }
        Player player = new Player(socket);
        socketPlayerMap.put(socket, player);
        return player;
    }

    public Game getGameByPlayer(Player player) {
        return playerGameMap.get(player);
    }

    public void createGame(Socket socket) {
        List<Player> list = new ArrayList<>();
        list.add(getPlayer(socket));
        Game game = new Game(list);
        games.add(game);
        playerGameMap.put(getPlayer(socket), game);
    }

    public void removeGame(Socket socket) {
        Game game = playerGameMap.get(getPlayer(socket));
        games.remove(game);
        for (Player player : game.getPlayers()) {
            playerGameMap.remove(player);
        }
    }

    public void joinGame(Socket socket, Game game) {
        playerGameMap.put(getPlayer(socket), game);
        game.addPlayer(getPlayer(socket));
    }

    public void quitGame(Socket socket) {
        Game game = playerGameMap.get(getPlayer(socket));
        if (game == null) {
            return;
        }
        game.removePlayer(getPlayer(socket));
        playerGameMap.remove(getPlayer(socket));
    }

    public Game getGameByID(int id){
        return games.stream().filter(game -> game.getId() == id).findFirst().orElse(null);
    }

    public void sendMessageToPlayer(Player player, String message) throws IOException {
        player.getSocket().getOutputStream().write((message + "\n").getBytes());
    }

    public void broadcastMessageToGame(Game game, String message) throws IOException {
        for (Player player : game.getPlayers()) {
            sendMessageToPlayer(player, message);
        }
    }

    public void finishGame(Game game) throws IOException {
        broadcastMessageToGame(game, "Game finished!");
        games.remove(game);
        for (Player player : game.getPlayers()) {
            playerGameMap.remove(player);
        }
    }
}
