package ro.tedyst.repositories;

import jakarta.persistence.EntityManager;
import ro.tedyst.Database;
import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;

import java.util.List;

public class ArtistRepository implements AbstractRepository<Artist> {
    public Artist findByID(Long id) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.find(Artist.class, id);
        }
    }

    public List<Artist> findByName(String name) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.createQuery("SELECT a FROM Artist a WHERE a.name LIKE :name", Artist.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }
}
