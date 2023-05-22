package ro.tedyst;

import ro.tedyst.commands.*;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final Map<String, Command> commands = new HashMap<>(
        Map.of(
                "setname", new SetNameCommand(),
                "join", new JoinGameCommand(),
                "quit", new QuitGameCommand(),
                "stop", new StopCommand(),
                "create", new CreateGameCommand(),
                "show", new ShowBoardCommand(),
                "place", new PlaceCommand()
        )
    );

    public static void runCommand(Socket socket, Server s, String message) throws IOException {
        if(message == null) {
            socket.getOutputStream().write("Empty message!\n".getBytes());
            return;
        }
        for (String commandName : commands.keySet()) {
            if (message.startsWith(commandName)) {
                commands.get(commandName).execute(socket, s, message);
                return;
            }
        }
        socket.getOutputStream().write(("Unknown command \"" + message + "\"!\n").getBytes());
    }
}
