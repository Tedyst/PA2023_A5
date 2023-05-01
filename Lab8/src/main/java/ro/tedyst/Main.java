package ro.tedyst;

import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;
import ro.tedyst.repositories.AlbumRepository;
import ro.tedyst.repositories.ArtistRepository;
import ro.tedyst.utils.ImportAlbumFromCSV;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Artist artist = new Artist();
        artist.setName("Artist 1");
        try {
            Connection connection = Database.getConn();
            DatabaseUtils.initDatabase(connection);
            connection.close();
            ArtistRepository.saveArtist(artist);
            Artist artist2 = ArtistRepository.getArtistByID(artist.getId());
            if(artist2 != null)
                System.out.println(artist2.getName() + " " + artist2.getId());

            ImportAlbumFromCSV.importAlbumsFromCSV("src/main/resources/albumlist.csv");

            for(Album album : AlbumRepository.getAllAlbums()) {
                System.out.println(album.getTitle() + " " + album.getArtist().getName() + " " + album.getGenre().getName());
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.toString());
        }
    }
}