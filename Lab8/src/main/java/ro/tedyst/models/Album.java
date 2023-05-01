package ro.tedyst.models;

import java.util.List;

public class Album {
    private Long id;
    private Integer releaseYear;
    private String title;
    private Genre genre;
    private Artist artist;

    public Integer getReleaseYear() {
        return releaseYear;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
