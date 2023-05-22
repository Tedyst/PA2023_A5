package ro.tedyst.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public void run(String serverAddress, int port) throws IOException {
        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true) ) {
            new ReadingThread(socket.getInputStream()).start();
            Scanner sc = new Scanner(System.in);
            while(true) {
                String request = sc.nextLine();
                if(Objects.equals(request, "exit"))
                    break;
                out.println(request);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
