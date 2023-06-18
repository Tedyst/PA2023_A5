package ro.tedyst;

import ro.tedyst.commands.DisplayLocales;
import ro.tedyst.commands.ExitCommand;
import ro.tedyst.commands.InfoLocaleCommand;
import ro.tedyst.commands.SetLocale;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static Map<String, Command> commands = new HashMap<>(
            Map.of(
                    "exit", new ExitCommand(),
                    "set-locale", new SetLocale(),
                    "info-locale", new InfoLocaleCommand(),
                    "display-locales", new DisplayLocales()
            )
    );
    public static void main(String[] args) throws IOException {
        LocaleExplore localeExplore = new LocaleExplore();
        Client client = new Client();
        while(true) {
            System.out.println(localeExplore.getMessage(client.getLocale(), "prompt"));
            String command = new Scanner(System.in).nextLine();
            String[] commandSplit = command.split(" ");
            if(commandSplit.length == 0){
                continue;
            }
            String commandName = commandSplit[0];
            Command commandToExecute = commands.get(commandName);
            if(commandToExecute == null){
                System.out.println(localeExplore.getMessage(client.getLocale(), "invalid-command"));
                continue;
            }
            String result = commandToExecute.execute(localeExplore, client, command);
            System.out.println(result);
        }
    }
}