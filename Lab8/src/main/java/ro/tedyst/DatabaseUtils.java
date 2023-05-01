package ro.tedyst;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    public static void initDatabase(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS artists (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(50) NOT NULL " +
                        ")"
        );
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS genres (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(50) NOT NULL " +
                        ")"
        );
        stmt.execute(
                "CREATE TABLE IF NOT EXISTS albums (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "title VARCHAR(50) NOT NULL, " +
                        "release_year INT NOT NULL, " +
                        "artist_id INT NOT NULL, " +
                        "genre_id INT NOT NULL, " +
                        "FOREIGN KEY (artist_id) REFERENCES artists(id)," +
                        "FOREIGN KEY (genre_id) REFERENCES genres(id)" +
                        ")"
        );
    }
}
