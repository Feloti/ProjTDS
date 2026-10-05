package com.newsaggregator.model.dao;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.newsaggregator.model.entities.News;

@Repository
public class NewsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public News save(News news) {
        entityManager.persist(news);
        return news;
    }

    @Transactional
    public News update(News news) {
        return entityManager.merge(news);
    }

    @Transactional
    public void delete(Long id) {
        News news = entityManager.find(News.class, id);
        if (news != null) {
            entityManager.remove(news);
        }
    }

    public Optional<News> findById(Long id) {
        return Optional.ofNullable(entityManager.find(News.class, id));
    }

    public List<News> findAll() {
        return entityManager
            .createQuery("SELECT n FROM News n", News.class)
            .getResultList();
    }
}