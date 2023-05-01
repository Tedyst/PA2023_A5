package ro.tedyst.repositories;

import ro.tedyst.Database;
import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {
    public static Album getAlbumByID(Long id) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, name, artist_id, release_year, genre_id FROM albums WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Album album = new Album();
            album.setId(rs.getLong("id"));
            album.setTitle(rs.getString("title"));
            album.setArtist(ArtistRepository.getArtistByID(rs.getLong("artist_id")));
            album.setReleaseYear(rs.getInt("release_year"));
            album.setGenre(GenreRepository.getGenreByID(rs.getLong("genre_id")));
            connection.close();
            return album;
        }
    }

    public static void createAlbum(Album album) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO albums (title, artist_id, release_year, genre_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, album.getTitle());
            if (album.getArtist() == null)
                stmt.setNull(2, Types.BIGINT);
            else
                stmt.setLong(2, album.getArtist().getId());
            stmt.setInt(3, album.getReleaseYear());
            stmt.setLong(4, album.getGenre().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                connection.close();
                return;
            }
            album.setId(rs.getLong(1));
        }
    }

    public static void saveAlbum(Album album) throws SQLException {
        GenreRepository.saveGenre(album.getGenre());
        ArtistRepository.saveArtist(album.getArtist());
        if(album.getId() == null || getAlbumByID(album.getId()) == null)
            createAlbum(album);
        else
            updateAlbum(album);
    }

    public static void updateAlbum(Album album) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE albums SET title = ?, artist_id = ?, release_year = ?, genre_id = ? WHERE id = ?");
            stmt.setString(1, album.getTitle());
            stmt.setLong(2, album.getArtist().getId());
            stmt.setInt(3, album.getReleaseYear());
            stmt.setLong(4, album.getGenre().getId());
            stmt.setLong(5, album.getId());
            stmt.executeUpdate();
        }
    }

    public static List<Album> getAllAlbums() throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, title, artist_id, release_year, genre_id FROM albums");
            ResultSet rs = stmt.executeQuery();
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                Album album = new Album();
                album.setId(rs.getLong("id"));
                album.setTitle(rs.getString("title"));
                album.setArtist(ArtistRepository.getArtistByID(rs.getLong("artist_id")));
                album.setReleaseYear(rs.getInt("release_year"));
                album.setGenre(GenreRepository.getGenreByID(rs.getLong("genre_id")));
                albums.add(album);
            }
            return albums;
        }
    }
}
