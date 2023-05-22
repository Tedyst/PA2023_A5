package ro.tedyst.commands;

import ro.tedyst.Server;

import java.io.IOException;
import java.net.Socket;

public class SetNameCommand implements Command {
    @Override
    public void execute(Socket socket, Server s, String request) {
        try {
            socket.getOutputStream().write(("Trying to set name to " + request + "\n").getBytes());
            if(s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)) != null) {
                socket.getOutputStream().write(("You are already in a game!\n").getBytes());
                return;
            }
            var vars = request.split(" ");
            if (vars.length == 1) {
                socket.getOutputStream().write(("Invalid name\n").getBytes());
                return;
            }
            s.getGameManager().getPlayer(socket).setName(vars[1]);
            socket.getOutputStream().write(("Name set to " + request + "\n").getBytes());
        } catch (IOException e) {
            System.out.println("Error while executing SetNameCommand: " + e);
        }
    }
}
