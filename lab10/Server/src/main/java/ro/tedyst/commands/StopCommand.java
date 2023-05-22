package ro.tedyst.commands;

import ro.tedyst.ClientThread;
import ro.tedyst.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class StopCommand implements Command {
    @Override
    public void execute(Socket socket, Server s, String request) {
        try {
            socket.getOutputStream().write("Server stopped!\n".getBytes());
            for (ClientThread client : s.getClients()) {
                client.getSocket().getOutputStream().write("Server stopped!\n".getBytes());
                client.getSocket().close();
            }
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error while executing StopCommand: " + e);
        }
    }
}
