package ro.tedyst.lab11;

import ro.tedyst.lab11.exceptions.InvalidGameException;
import ro.tedyst.lab11.models.Game;
import ro.tedyst.lab11.models.Player;

public class GameManager {
    public static void joinGame(Player player, Game game) throws InvalidGameException {
        game.addPlayer(player);
    }

    public static void removeFromGame(Player player, Game game) throws InvalidGameException {
        game.removePlayer(player);
    }
}
