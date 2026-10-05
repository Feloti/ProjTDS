package com.newsaggregator.model.dao;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.newsaggregator.model.entities.ReadHistory;

@Repository
public class ReadHistoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public ReadHistory save(ReadHistory readHistory) {
        entityManager.persist(readHistory);
        return readHistory;
    }

    @Transactional
    public ReadHistory update(ReadHistory readHistory) {
        return entityManager.merge(readHistory);
    }

    @Transactional
    public void delete(Long id) {
        ReadHistory readHistory = entityManager.find(ReadHistory.class, id);
        if (readHistory != null) {
            entityManager.remove(readHistory);
        }
    }

    public Optional<ReadHistory> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ReadHistory.class, id));
    }

    public List<ReadHistory> findAll() {
        return entityManager
            .createQuery("SELECT r FROM ReadHistory r", ReadHistory.class)
            .getResultList();
    }
}