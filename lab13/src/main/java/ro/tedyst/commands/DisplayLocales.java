package ro.tedyst.commands;

import ro.tedyst.Client;
import ro.tedyst.Command;
import ro.tedyst.LocaleExplore;
import ro.tedyst.LocaleTypes;

public class DisplayLocales implements Command {
    @Override
    public String execute(LocaleExplore localeExplore, Client client, String command) {
        StringBuilder sb = new StringBuilder();
        sb.append(localeExplore.getMessage(client.getLocale(), "locales-available")).append("\n");
        for(LocaleTypes locale : LocaleTypes.values()){
            sb.append(locale).append("\n");
        }
        return sb.toString();
    }
}
