package ro.tedyst;

public class Company implements Node {
    private int id;
    private String name;

    public Company(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param company 
     * @return
     */
    @Override
    public int compareTo(Node node) {
        return name.compareTo(node.getName());
    }

    /**
     * @return 
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return 
     */
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (getId() != company.getId()) return false;
        return getName() != null ? getName().equals(company.getName()) : company.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
