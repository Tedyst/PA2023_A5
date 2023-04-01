package ro.tedyst.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.tedyst.Catalog;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements GenericCommand {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getCommand() {
        return "save";
    }

    public void runCommand(String path, Catalog c) throws CommandException {
        try {
            mapper.writeValue(new File(path), c);
        } catch (IOException e) {
            System.out.println("Exception while saving Catalog: " + e);
            throw new CommandException(e.toString());
        }
    }
}
