package ro.tedyst;

public interface Command {
    String execute(LocaleExplore localeExplore, Client client, String command);
}
