package ro.tedyst.commands;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements GenericCommand {
    @Override
    public String getCommand() {
        return "view";
    }

    public void runCommand(String path) throws CommandException {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            System.out.println("Exception while opening file: " + e);
            throw new CommandException(e.toString());
        }
    }
}
