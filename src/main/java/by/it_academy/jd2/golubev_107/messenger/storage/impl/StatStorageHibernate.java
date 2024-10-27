package by.it_academy.jd2.golubev_107.messenger.storage.impl;

import by.it_academy.jd2.golubev_107.messenger.storage.IStatStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.HibernateManager;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.StatCounter;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StatStorageHibernate implements IStatStorage {

    private final HibernateManager hibernateManager;

    public StatStorageHibernate(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public StatCounter readByName(String name) {
        return hibernateManager.inTransaction(manager -> {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<StatCounter> query = criteriaBuilder.createQuery(StatCounter.class);
            Root<StatCounter> statCounterRoot = query.from(StatCounter.class);
            query.where(criteriaBuilder.equal(statCounterRoot.get("name"), name));
            try {
                return manager.createQuery(query).getSingleResult();
            } catch (NoResultException e) {
                throw new IllegalStateException("There is no such stat counter has been created: " + name);
            }
        });
    }

    @Override
    public void update(StatCounter statCounter) {
        hibernateManager.inTransaction(manager -> manager.merge(statCounter));
    }
}
