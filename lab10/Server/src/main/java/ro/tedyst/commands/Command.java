package ro.tedyst.commands;

import ro.tedyst.Server;

import java.io.OutputStream;
import java.net.Socket;

public interface Command {
    void execute(Socket socket, Server s, String request);
}
