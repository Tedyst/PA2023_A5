package ro.tedyst.repositories;

import ro.tedyst.Database;
import ro.tedyst.models.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRepository {
    public static Genre getGenreByID(Long id) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM genres WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                connection.close();
                return null;
            }

            Genre genre = new Genre();
            genre.setId(rs.getLong("id"));
            genre.setName(rs.getString("name"));
            return genre;
        }
    }

    public static void createGenre(Genre genre) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO genres (name) VALUES (?)");
            stmt.setString(1, genre.getName());
            stmt.executeUpdate();
        }
    }

    public static void saveGenre(Genre genre) throws SQLException {
        if(genre.getId() == null || getGenreByID(genre.getId()) == null)
            createGenre(genre);
        else
            updateGenre(genre);
    }

    public static void updateGenre(Genre genre) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE genres SET name = ? WHERE id = ?");
            stmt.setString(1, genre.getName());
            stmt.setLong(2, genre.getId());
        }
    }

    public static Genre getGenreByName(String name) throws SQLException {
        try(Connection connection = Database.getConn()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM genres WHERE name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                connection.close();
                return null;
            }

            Genre genre = new Genre();
            genre.setId(rs.getLong("id"));
            genre.setName(rs.getString("name"));
            connection.close();
            return genre;
        }
    }
}
