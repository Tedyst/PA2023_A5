package ro.tedyst.repositories;

import jakarta.persistence.EntityManager;
import ro.tedyst.Database;
import ro.tedyst.models.Album;
import ro.tedyst.models.Genre;

import java.util.List;

public class GenreRepository implements AbstractRepository<Genre> {
    public Genre findByID(Long id) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.find(Genre.class, id);
        }
    }

    public List<Genre> findByName(String name) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            return em.createQuery("SELECT g FROM Genre g WHERE g.name LIKE :name", Genre.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }
}
