package ro.tedyst.commands;

import ro.tedyst.Server;

import java.io.IOException;
import java.net.Socket;

public class CreateGameCommand implements Command {
    @Override
    public void execute(Socket socket, Server s, String request) {
        try {
            socket.getOutputStream().write(("Trying to create game\n").getBytes());
            if(s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)) != null) {
                socket.getOutputStream().write(("You are already in a game!\n").getBytes());
                return;
            }
            s.getGameManager().createGame(socket);
            socket.getOutputStream().write(("Game created with id " +
                    s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)).getId()
                    + "\n").getBytes());
        } catch (IOException e) {
            System.out.println("Error while executing CreateGameCommand: " + e);
        }
    }
}
