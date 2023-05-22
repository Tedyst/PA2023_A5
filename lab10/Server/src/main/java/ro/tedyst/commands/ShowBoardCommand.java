package ro.tedyst.commands;

import ro.tedyst.Server;
import ro.tedyst.models.Board;

import java.net.Socket;

public class ShowBoardCommand implements Command {
    public void execute(Socket socket, Server s, String request){
        try {
            socket.getOutputStream().write(("Trying to show board\n").getBytes());
            if(s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)) == null) {
                socket.getOutputStream().write(("You are not in a game!\n").getBytes());
                return;
            }
            Board board = s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)).getBoard();
            socket.getOutputStream().write(("Board:\n").getBytes());
            socket.getOutputStream().write((board.toString()).getBytes());
            socket.getOutputStream().write(("\n").getBytes());
        } catch (Exception e) {
            System.out.println("Error while executing ShowBoardCommand: " + e);
        }
    }
}
