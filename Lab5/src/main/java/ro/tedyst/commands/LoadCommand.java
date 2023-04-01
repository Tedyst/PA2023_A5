package ro.tedyst.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.tedyst.Catalog;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements GenericCommand {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getCommand() {
        return "load";
    }

    public Catalog runCommand(String path, Catalog c) throws CommandException {
        try {
            return mapper.readValue(new File(path), Catalog.class);
        } catch (IOException e) {
            System.out.println("Exception while loading Catalog: " + e);
            throw new CommandException(e.toString());
        }
    }
}
