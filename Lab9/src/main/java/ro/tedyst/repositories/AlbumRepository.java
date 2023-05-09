package ro.tedyst.repositories;

import jakarta.persistence.EntityManager;
import ro.tedyst.Database;
import ro.tedyst.models.Album;

import java.util.List;

public class AlbumRepository implements AbstractRepository<Album> {
    public Album findByID(Long id) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.find(Album.class, id);
        }
    }

    public List<Album> findByName(String name) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.createQuery("SELECT a FROM Album a WHERE a.name LIKE :name", Album.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }
}
