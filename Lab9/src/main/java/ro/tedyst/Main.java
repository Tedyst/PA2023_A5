package ro.tedyst;

import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;
import ro.tedyst.models.Genre;
import ro.tedyst.repositories.AlbumRepository;
import ro.tedyst.repositories.ArtistRepository;
import ro.tedyst.repositories.GenreRepository;

public class Main {
    public static void main(String[] args) {
        Album u = new Album();
        Artist a = new Artist();
        a.setName("test");
        u.setTitle("album test");
        Genre g = new Genre();
        g.setName("test");
        u.setArtist(a);
        u.setGenre(g);
        new ArtistRepository().create(a);
        new GenreRepository().create(g);
        new AlbumRepository().create(u);
        long currentTime = System.currentTimeMillis();
        for(int i=0; i < 1000; i++){
            Album u2 = new Album();
            u2.setArtist(a);
            u2.setGenre(g);
            u2.setTitle("album test " + i);
            new AlbumRepository().create(u2);
        }
        System.out.println("Took " + (System.currentTimeMillis() - currentTime) + " milliseconds");
        System.out.println(new AlbumRepository().findByID(1L).getArtist().getName());
    }
}