package ro.tedyst.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {
    public static final String BASE_URL = "http://localhost:5000";
    public static void main(String[] args) throws URISyntaxException, IOException {
        String name = "Tedy";
        Long game = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Enter command:");
            String command = br.readLine();
            if (command.length() == 0) {
                continue;
            }
            if (command.equals("exit")) {
                break;
            }
            if (command.startsWith("setname")){
                name = command.substring(8);
                System.out.println("Name set to " + name);
            }
            if (command.startsWith("create")) {
                System.out.println("Creating game...");
                String url_str = BASE_URL + "/games";
                URL url = new URI(url_str).toURL();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                String json = "";
                con.getOutputStream().write(json.getBytes());
                int responseCode = con.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error creating game: " + responseCode);
                    continue;
                }
                String response = new String(con.getInputStream().readAllBytes());
                JsonElement jsonElement = new JsonParser().parse(response);
                System.out.println("Game created with id " + jsonElement.getAsJsonObject().get("id").getAsLong());
                game = jsonElement.getAsJsonObject().get("id").getAsLong();
            }
            if (command.startsWith("list")) {
                if (game == null) {
                    System.out.println("No game selected");
                    continue;
                }
                System.out.println("Listing players...");
                String url_str = BASE_URL + "/games/" + game;
                URL url = new URI(url_str).toURL();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                int responseCode = con.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error listing players: " + responseCode);
                    System.out.println(new String(con.getErrorStream().readAllBytes()));
                    continue;
                }
                String response = new String(con.getInputStream().readAllBytes());
                JsonElement jsonElement = new JsonParser().parse(response);
                System.out.println("Players in game " + game + ":");
                for (JsonElement player : jsonElement.getAsJsonObject().get("players").getAsJsonArray()) {
                    System.out.println(player.getAsJsonObject().get("name").getAsString());
                }
            }
            if (command.startsWith("join")) {
                if (name.equals("")) {
                    System.out.println("No name set");
                    continue;
                }
                System.out.println("Joining game...");
                game = Long.parseLong(command.substring(5));
                String url_str = BASE_URL + "/player/" + name + "/join/" + game;
                URL url = new URI(url_str).toURL();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                int responseCode = con.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error joining game: " + responseCode);
                    continue;
                }
                String response = new String(con.getInputStream().readAllBytes());
                JsonElement jsonElement = new JsonParser().parse(response);
                System.out.println("Joined game " + game);
            }
            if (command.startsWith("delete")) {
                System.out.println("Deleting player...");
                String url_str = BASE_URL + "/player/" + name;
                URL url = new URI(url_str).toURL();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("DELETE");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                int responseCode = con.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error deleting player: " + responseCode);
                    continue;
                }
                String response = new String(con.getInputStream().readAllBytes());
                JsonElement jsonElement = new JsonParser().parse(response);
                System.out.println("Player deleted");
            }
            if (command.startsWith("board")) {
                if (game == null) {
                    System.out.println("No game selected");
                    continue;
                }
                System.out.println("Listing board...");
                String url_str = BASE_URL + "/games/" + game;
                URL url = new URI(url_str).toURL();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                int responseCode = con.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error listing board: " + responseCode);
                    System.out.println(new String(con.getErrorStream().readAllBytes()));
                    continue;
                }
                String response = new String(con.getInputStream().readAllBytes());
                JsonElement jsonElement = new JsonParser().parse(response);
                System.out.println("Board for game " + game + ":");
                for (JsonElement row : jsonElement.getAsJsonObject().get("board").getAsJsonObject().get("board").getAsJsonArray()) {
                    for (JsonElement cell : row.getAsJsonArray()) {
                        System.out.print(cell.getAsInt() + " ");
                    }
                    System.out.println();
                }

            }
        }
    }
}