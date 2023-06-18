package ro.tedyst.commands;

import ro.tedyst.Client;
import ro.tedyst.Command;
import ro.tedyst.LocaleExplore;
import ro.tedyst.LocaleTypes;

import java.io.IOException;
import java.text.MessageFormat;

public class SetLocale implements Command {
    @Override
    public String execute(LocaleExplore localeExplore, Client client, String command) {
        String[] commandSplit = command.split(" ");
        if(commandSplit.length != 2){
            return localeExplore.getMessage(client.getLocale(), "set-locale-invalid-arguments");
        }
        LocaleTypes locale = null;
        try {
            locale = ro.tedyst.LocaleTypes.valueOf(commandSplit[1].toUpperCase());
        } catch(IllegalArgumentException e){
            return localeExplore.getMessage(client.getLocale(), "set-locale-invalid-locale");
        }
        if(locale == null){
            return localeExplore.getMessage(client.getLocale(), "set-locale-invalid-locale");
        }
        client.setLocale(locale);
        return MessageFormat.format(localeExplore.getMessage(client.getLocale(), "locale-set-to"), locale);
    }
}
