package ro.tedyst;

import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;
import ro.tedyst.models.Genre;
import ro.tedyst.repositories.AlbumRepository;
import ro.tedyst.repositories.ArtistRepository;
import ro.tedyst.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
        for(int i=0; i < 1000; i++){
            Album u = new Album();
            Artist a = new Artist();
            a.setName("test" + i);
            u.setTitle("album test" + i);
            Genre g = new Genre();
            g.setName("test" + i);
            u.setArtist(a);
            List<Genre> genres = new ArrayList<>();
            genres.add(g);
            u.setGenres(genres);
            new ArtistRepository().create(a);
            new GenreRepository().create(g);
            new AlbumRepository().create(u);
        }
        System.out.println("Took " + (System.currentTimeMillis() - currentTime) + " milliseconds");
        System.out.println(new AlbumRepository().findByID(1L).getArtist().getName());
    }
}