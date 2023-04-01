package ro.tedyst;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    protected List<Document> documents;

    public Catalog(){
        documents = new ArrayList<>();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "documents=" + documents +
                '}';
    }
}
