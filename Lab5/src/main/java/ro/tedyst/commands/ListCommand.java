package ro.tedyst.commands;

import ro.tedyst.Catalog;
import ro.tedyst.CatalogException;
import ro.tedyst.Document;

public class ListCommand implements GenericCommand {
    @Override
    public String getCommand() {
        return "list";
    }

    public void runCommand(Catalog c) {
        for(Document d : c.getDocuments())
            System.out.println(d);
    }
}
