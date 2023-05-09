package ro.tedyst.repositories;

import jakarta.persistence.EntityManager;
import ro.tedyst.Database;
import ro.tedyst.models.Album;
import ro.tedyst.models.Artist;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public interface AbstractRepository<T> {
    default void create(T u) {
        try(EntityManager em = Database.getEntityManagerFactory().createEntityManager()){
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        }
    }
    T findByID(Long id);
    List<T> findByName(String name);
}
