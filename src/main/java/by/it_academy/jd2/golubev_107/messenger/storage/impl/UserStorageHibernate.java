package by.it_academy.jd2.golubev_107.messenger.storage.impl;

import by.it_academy.jd2.golubev_107.messenger.storage.IUserStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.HibernateManager;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.UUID;

public class UserStorageHibernate implements IUserStorage {

    private final HibernateManager hibernateManager;

    public UserStorageHibernate(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public User save(User user) {
        return hibernateManager.inTransaction(manager -> {
            manager.persist(user);
            return user;
        });
    }

    @Override
    public User update(User user) {
        return hibernateManager.inTransaction(manager -> manager.merge(user));
    }

    @Override
    public User readById(UUID id) {
        return hibernateManager.inTransaction(manager -> manager.find(User.class, id));
    }

    @Override
    public User readByLogin(String login) {
        return hibernateManager.inTransaction(manager -> {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = query.from(User.class);
            query.where(criteriaBuilder.equal(userRoot.get("login"), login));
            try {
                return manager.createQuery(query).getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    @Override
    public long countAll() {
        return hibernateManager.inTransaction(manager -> {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(builder.count(userRoot.get("id")));
            return manager.createQuery(criteriaQuery).getSingleResult();
        });
    }
}
