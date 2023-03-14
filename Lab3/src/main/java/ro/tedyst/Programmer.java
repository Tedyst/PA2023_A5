package ro.tedyst;

import java.util.Date;

public class Programmer extends Person {
    protected String githubLink;

    public Programmer(int id, String name, Date birthDate, String githubLink){
        super(id, name, birthDate);
        this.githubLink = githubLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "githubLink='" + githubLink + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
