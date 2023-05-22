package ro.tedyst.models;

import java.net.Socket;

public class Player {
    private Socket socket;
    private String name = "UNKNOWN";

    public Player(Socket socket) {
        this.socket = socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }
}
