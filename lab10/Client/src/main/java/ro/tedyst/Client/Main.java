package ro.tedyst.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client c = new Client();
        c.run("127.0.0.1", 8100);
    }
}