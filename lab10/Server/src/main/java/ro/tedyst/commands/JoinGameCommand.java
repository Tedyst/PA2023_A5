package ro.tedyst.commands;

import ro.tedyst.ClientThread;
import ro.tedyst.Server;
import ro.tedyst.models.Game;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class JoinGameCommand implements Command {
    @Override
    public void execute(Socket socket, Server s, String request) {
        try {
            int gameId;
            try {
                var vars = request.split(" ");
                if(vars.length == 1)
                    throw new NumberFormatException();
                gameId = Integer.parseInt(vars[1]);
            } catch (NumberFormatException e) {
                socket.getOutputStream().write(("Invalid game id\n").getBytes());
                return;
            }
            Game existingGame = s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket));
            if (existingGame != null) {
                socket.getOutputStream().write(("You are already in a game!\n").getBytes());
                return;
            }
            socket.getOutputStream().write(("Trying to join game " + gameId + "\n").getBytes());
            Game game = s.getGameManager().getGameByID(gameId);
            if (game == null) {
                socket.getOutputStream().write(("Game " + gameId + " does not exist\n").getBytes());
                return;
            }
            if (game.isStarted()) {
                socket.getOutputStream().write(("Game " + gameId + " has already started\n").getBytes());
                return;
            }
            s.getGameManager().joinGame(socket, game);
            socket.getOutputStream().write(("Joined game " + gameId + "\n").getBytes());
            s.getGameManager().broadcastMessageToGame(game, "Player " + s.getGameManager().getPlayer(socket).getName() + " joined the game");
        } catch (IOException e) {
            System.out.println("Error while executing JoinGameCommand: " + e);
        }
    }
}
