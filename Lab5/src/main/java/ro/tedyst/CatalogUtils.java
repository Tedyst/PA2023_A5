package ro.tedyst;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CatalogUtils {
    ObjectMapper mapper = new ObjectMapper();

    public void add(Catalog c, Document d) throws CatalogException {
        List<Document> l = c.getDocuments();
        l.add(d);
        c.setDocuments(l);
    }

    public void save(Catalog c, String path) throws IOException {
        mapper.writeValue(new File(path), c);
    }

    public Catalog load(String path) throws IOException {
        Catalog c = mapper.readValue(new File(path), Catalog.class);
        return c;
    }
}
