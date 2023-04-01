package ro.tedyst;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;


public class Document {
    protected int id;
    protected String name;
    protected Map<String, String> tags;

    protected String path;


    public Document(int id, String name, String path, Map<String, String> tags) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.path = path;
    }

    private Document(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addTag(String tag, String value) {
        tags.put(tag, value);
    }

    public void removeTag(String tag) throws CatalogException {
        if(!tags.containsKey(tag))
            throw new CatalogException("Tag does not exist");
        tags.remove(tag);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                '}';
    }
}
