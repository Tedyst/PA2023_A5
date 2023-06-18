package ro.tedyst.commands;

import ro.tedyst.Client;
import ro.tedyst.Command;
import ro.tedyst.LocaleExplore;

public class ExitCommand implements Command {
    @Override
    public String execute(LocaleExplore localeExplore, Client client, String command) {
        System.out.println(localeExplore.getMessage(client.getLocale(), "exit"));
        System.exit(0);
        return "";
    }
}
