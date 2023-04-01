package ro.tedyst.commands;

import ro.tedyst.Catalog;

public class ToStringCommand implements GenericCommand {
    @Override
    public String getCommand() {
        return "toString";
    }

    public String runCommand(Catalog c) {
        return c.toString();
    }
}
