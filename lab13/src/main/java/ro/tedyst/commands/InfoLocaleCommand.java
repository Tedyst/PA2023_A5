package ro.tedyst.commands;

import ro.tedyst.Client;
import ro.tedyst.Command;
import ro.tedyst.LocaleExplore;

import java.text.MessageFormat;

public class InfoLocaleCommand implements Command {
    @Override
    public String execute(LocaleExplore localeExplore, Client client, String command) {
        return MessageFormat.format(localeExplore.getMessage(client.getLocale(), "info-locale"), client.getLocale());
    }
}
