package by.it_academy.jd2.golubev_107.messenger.storage.impl;

import by.it_academy.jd2.golubev_107.messenger.storage.IMessageStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.HibernateManager;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.Message;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.UUID;

public class MessageStorageHibernate implements IMessageStorage {

    private final HibernateManager hibernateManager;

    public MessageStorageHibernate(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public void save(Message message) {
        hibernateManager.inTransaction(manager -> {
            if (message.getId() == null) {
                manager.persist(message);
            } else {
                manager.merge(message);
            }
            return message;
        });
    }

    @Override
    public Message readById(Long id) {
        return hibernateManager.inTransaction(manager -> manager.find(Message.class, id));
    }

    @Override
    public List<Message> readAllReceivedByUser(UUID userId) {
        return hibernateManager.inTransaction(manager -> {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
            Root<Message> messageRoot = criteriaQuery.from(Message.class);
            Join<Message, User> userToRoot = messageRoot.join("to", JoinType.INNER);

            criteriaQuery.multiselect(messageRoot, userToRoot)
                         .where(builder.equal(userToRoot.get("id"), userId));

            return manager.createQuery(criteriaQuery).getResultStream()
                          .map(tuple -> tuple.get(0, Message.class))
                          .toList();
        });
    }

    @Override
    public long countAll() {
        return hibernateManager.inTransaction(manager -> {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<Message> messageRoot = criteriaQuery.from(Message.class);
            criteriaQuery.select(builder.count(messageRoot.get("id")));
            return manager.createQuery(criteriaQuery).getSingleResult();
        });
    }
}
