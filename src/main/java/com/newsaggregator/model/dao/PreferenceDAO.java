package com.newsaggregator.model.dao;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.newsaggregator.model.entities.Preference;

@Repository
public class PreferenceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Preference save(Preference preference) {
        entityManager.persist(preference);
        return preference;
    }

    @Transactional
    public Preference update(Preference preference) {
        return entityManager.merge(preference);
    }

    @Transactional
    public void delete(Long id) {
        Preference preference = entityManager.find(Preference.class, id);
        if (preference != null) {
            entityManager.remove(preference);
        }
    }

    public Optional<Preference> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Preference.class, id));
    }

    public List<Preference> findAll() {
        return entityManager
            .createQuery("SELECT p FROM Preference p", Preference.class)
            .getResultList();
    }
}