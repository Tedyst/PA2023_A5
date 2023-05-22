package ro.tedyst.commands;

import ro.tedyst.GameManager;
import ro.tedyst.Server;
import ro.tedyst.models.Board;
import ro.tedyst.models.Game;
import ro.tedyst.models.Player;

import java.net.Socket;

public class PlaceCommand implements Command {
    @Override
    public void execute(Socket socket, Server s, String request){
        try {
            socket.getOutputStream().write(("Trying to place a piece\n").getBytes());
            if(s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)) == null) {
                socket.getOutputStream().write(("You are not in a game!\n").getBytes());
                return;
            }
            String[] args = request.split(" ");
            if(args.length != 3) {
                socket.getOutputStream().write(("Invalid number of arguments!\n").getBytes());
                return;
            }
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            if(x < 0 || x > Board.getBoardSize() || y < 0 || y > Board.getBoardSize()) {
                socket.getOutputStream().write(("Invalid coordinates!\n").getBytes());
                return;
            }
            if(!s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)).getBoard().placePiece(
                    s.getGameManager().getPlayer(socket), x, y
            )) {
                socket.getOutputStream().write(("Invalid move!\n").getBytes());
                return;
            }
            socket.getOutputStream().write(("Piece placed!\n").getBytes());
            s.getGameManager().broadcastMessageToGame(
                    s.getGameManager().getGameByPlayer(s.getGameManager().getPlayer(socket)),
                    "Player " + s.getGameManager().getPlayer(socket).getName() + " placed a piece at " + x + " " + y + "\n"
            );

            Player player = s.getGameManager().getPlayer(socket);
            Game game = s.getGameManager().getGameByPlayer(player);
            if(!game.removeTimeFromPlayer(player)) {
                s.getGameManager().broadcastMessageToGame(game, "Player " + player.getName() + " ran out of time!");
                s.getGameManager().finishGame(game);
            } else {
                s.getGameManager().broadcastMessageToGame(game, "Player " + player.getName() + " has " + game.getRemainingTime(player) + " miliseconds left");
            }

            if(game.getBoard().won(player)){
                s.getGameManager().broadcastMessageToGame(game, "Player " + player.getName() + " won!");
                s.getGameManager().finishGame(game);
            }

        } catch (Exception e) {
            System.out.println("Error while executing PlaceCommand: " + e);
        }
    }
}
