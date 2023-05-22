package ro.tedyst.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadingThread extends Thread {
    final InputStream inputStream;

    public ReadingThread(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))){
            while(true) {
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (IOException ignored) {

        }
    }
}
