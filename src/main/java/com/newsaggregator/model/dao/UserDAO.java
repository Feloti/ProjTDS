package com.newsaggregator.model.dao;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.newsaggregator.model.entities.User;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public List<User> findAll() {
        return entityManager
            .createQuery("SELECT u FROM User u", User.class)
            .getResultList();
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = entityManager
            .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
            .setParameter("email", email)
            .getResultList();
        return users.stream().findFirst();
    }

    public boolean existsByEmail(String email) {
        Long total = entityManager
            .createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
            .setParameter("email", email)
            .getSingleResult();
        return total > 0;
    }
}