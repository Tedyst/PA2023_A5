package ro.tedyst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null ;
    private final Server server;
    public ClientThread (Socket socket, Server server) { this.socket = socket; this.server = server; }
    public void run () {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                String request = in.readLine();
                CommandManager.runCommand(socket, server, request);
                socket.getOutputStream().flush();
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
            server.getGameManager().quitGame(socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println (e.toString());
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}