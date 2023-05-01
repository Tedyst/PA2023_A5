package ro.tedyst.repositories;

import org.junit.jupiter.api.Test;
import ro.tedyst.Database;
import ro.tedyst.DatabaseUtils;
import ro.tedyst.models.Artist;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ArtistRepositoryTest {

    @Test
    void testGetAndCreate() throws SQLException {
        DatabaseUtils.initDatabase(Database.getConn());
        Artist artist = new Artist();
        artist.setName("Artist 1");
        DatabaseUtils.initDatabase(Database.getConn());
        ArtistRepository.saveArtist(artist);
        Artist artist2 = ArtistRepository.getArtistByID(artist.getId());
        assert artist.equals(artist2);
    }
}