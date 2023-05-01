package ro.tedyst.utils;

import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;
import ro.tedyst.models.Genre;
import ro.tedyst.repositories.AlbumRepository;
import ro.tedyst.repositories.ArtistRepository;
import ro.tedyst.repositories.GenreRepository;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

public class ImportAlbumFromCSV {
    public static void importAlbumsFromCSV(String filename) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();
        while(br.ready()) {
            try {
                String line = br.readLine();
                String[] values = line.split(",");
                Album album = new Album();
                album.setReleaseYear(Integer.parseInt(values[1]));
                album.setTitle(values[2]);

                Artist artist = ArtistRepository.getArtistByName(values[3]);
                if(artist == null) {
                    artist = new Artist();
                    artist.setName(values[3]);
                    ArtistRepository.saveArtist(artist);
                }
                album.setArtist(artist);

                Genre genre = GenreRepository.getGenreByName(values[4]);
                if(genre == null) {
                    genre = new Genre();
                    genre.setName(values[4]);
                    GenreRepository.saveGenre(genre);
                }
                album.setGenre(genre);

                AlbumRepository.saveAlbum(album);
                System.out.println("Imported album " + album.getTitle());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }
}
