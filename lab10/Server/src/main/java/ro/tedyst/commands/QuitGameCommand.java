package ro.tedyst.commands;

import ro.tedyst.Server;
import ro.tedyst.models.Game;

import java.io.IOException;
import java.net.Socket;

public class QuitGameCommand implements Command {
    @Override
    public void execute(Socket socket, Server s, String request) {
        try {
            Game game = s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket));
            if (game == null) {
                socket.getOutputStream().write(("You are not in a game!\n").getBytes());
                return;
            }
            socket.getOutputStream().write(("Trying to quit game " + game.getId() + "!\n").getBytes());
            s.getGameManager().quitGame(socket);
            socket.getOutputStream().write(("Quit game " + game.getId() + "\n").getBytes());
        } catch (IOException e) {
            System.out.println("Error while executing QuitGameCommand: " + e);
        }
    }
}
