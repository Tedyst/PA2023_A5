package ro.tedyst;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import ro.tedyst.commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> tags1 = new HashMap<>();
        tags1.put("author", "Stoica Tedy");
        Catalog c = new Catalog();
        Document d1 = new Document(1, "test", "file:///etc/hosts", null);
        Document d2 = new Document(2, "test2", "https://tedyst.ro", tags1);

        CatalogUtils cu = new CatalogUtils();
        try {
            cu.add(c, d1);
        } catch (CatalogException e) {
            System.out.println("Exception while adding Document 1: " + e);
        }
        try {
            cu.add(c, d2);
        } catch (CatalogException e) {
            System.out.println("Exception while adding Document 2: " + e);
        }
        try {
            cu.save(c, "/home/tedy/Git/PA2023_A5/Lab5/catalog.json");
        } catch (IOException e) {
            System.out.println("Exception while saving Catalog: " + e);
        }

        try {
            Catalog c2 = cu.load("/home/tedy/Git/PA2023_A5/Lab5/catalog.json");
            System.out.println(c2);
        } catch (IOException e) {
            System.out.println("Exception while loading Catalog: " + e);
        }


        try {
            Catalog c1 = new Catalog();
            new AddCommand().runCommand(c1, d1);
            new AddCommand().runCommand(c1, d2);
            new ListCommand().runCommand(c1);
            new ReportCommand().runCommand(c1, "/tmp/test.html");
            new ViewCommand().runCommand("/tmp/test.html");
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }


    }
}