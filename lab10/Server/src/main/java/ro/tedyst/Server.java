package ro.tedyst;

import ro.tedyst.models.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORT = 8100;
    private final List<ClientThread> clients = new ArrayList<>();
    private GameManager gameManager = new GameManager();

    public void run() throws IOException {
        ServerSocket serverSocket = null ;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println ("Waiting for a client ...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from " + socket.getInetAddress() + ":" + socket.getPort() + "!");
                ClientThread clientThread = new ClientThread(socket, this);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public List<ClientThread> getClients() {
        return clients;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
