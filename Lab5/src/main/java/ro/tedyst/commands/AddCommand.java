package ro.tedyst.commands;

import ro.tedyst.Catalog;
import ro.tedyst.CatalogException;
import ro.tedyst.Document;

import java.io.IOException;
import java.util.List;

public class AddCommand implements GenericCommand {
    @Override
    public String getCommand() {
        return "add";
    }

    public void runCommand(Catalog c, Document d) throws CommandException {
        List<Document> l = c.getDocuments();
        if(l.contains(d))
            throw new CommandException("Document already exists in Catalog");
        l.add(d);
        c.setDocuments(l);
    }
}
