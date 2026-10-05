package com.newsaggregator.model.dao;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.newsaggregator.model.entities.Favorite;

@Repository
public class FavoriteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Favorite save(Favorite favorite) {
        entityManager.persist(favorite);
        return favorite;
    }

    @Transactional
    public Favorite update(Favorite favorite) {
        return entityManager.merge(favorite);
    }

    @Transactional
    public void delete(Long id) {
        Favorite favorite = entityManager.find(Favorite.class, id);
        if (favorite != null) {
            entityManager.remove(favorite);
        }
    }

    public Optional<Favorite> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Favorite.class, id));
    }

    public List<Favorite> findAll() {
        return entityManager
            .createQuery("SELECT f FROM Favorite f", Favorite.class)
            .getResultList();
    }
}