package ro.tedyst.repositories;

import ro.tedyst.Database;
import ro.tedyst.models.Artist;

import java.sql.*;

public class ArtistRepository {
    public static Artist getArtistByID(Long id) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM artists WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                connection.close();
                return null;
            }

            Artist artist = new Artist();
            artist.setId(rs.getLong("id"));
            artist.setName(rs.getString("name"));
            return artist;
        }
    }

    public static void createArtist(Artist artist) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO artists (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, artist.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                connection.close();
                return;
            }
            artist.setId(rs.getLong(1));
        }
    }

    public static void saveArtist(Artist artist) throws SQLException {
        if(artist.getId() == null || getArtistByID(artist.getId()) == null)
            createArtist(artist);
        else
            updateArtist(artist);
    }

    public static void updateArtist(Artist artist) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE artists SET name = ? WHERE id = ?");
            stmt.setString(1, artist.getName());
            stmt.setLong(2, artist.getId());
            stmt.executeUpdate();
        }
    }

    public static Artist getArtistByName(String name) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM artists WHERE name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next())
                return null;

            Artist artist = new Artist();
            artist.setId(rs.getLong("id"));
            artist.setName(rs.getString("name"));
            connection.close();
            return artist;
        }
    }
}
