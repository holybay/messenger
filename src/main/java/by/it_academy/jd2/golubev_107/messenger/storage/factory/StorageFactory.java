package by.it_academy.jd2.golubev_107.messenger.storage.factory;

import by.it_academy.jd2.golubev_107.messenger.storage.IMessageStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.IStatStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.IUserStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.HibernateManager;
import by.it_academy.jd2.golubev_107.messenger.storage.impl.MessageStorageHibernate;
import by.it_academy.jd2.golubev_107.messenger.storage.impl.StatStorageHibernate;
import by.it_academy.jd2.golubev_107.messenger.storage.impl.UserStorageHibernate;

public class StorageFactory implements AutoCloseable {

    private static final StorageFactory INSTANCE = new StorageFactory();
    private final IUserStorage userStorage;
    private final IMessageStorage messageStorage;
    private final HibernateManager hibernateManager;
    private final IStatStorage statStorage;

    public StorageFactory() {
        hibernateManager = new HibernateManager("unit");
        this.userStorage = new UserStorageHibernate(hibernateManager);
        this.messageStorage = new MessageStorageHibernate(hibernateManager);
        this.statStorage = new StatStorageHibernate(hibernateManager);
    }

    public static StorageFactory getInstance() {
        return INSTANCE;
    }

    public IUserStorage getUserStorage() {
        return userStorage;
    }

    public IMessageStorage getMessageStorage() {
        return messageStorage;
    }

    public IStatStorage getStatStorage() {
        return statStorage;
    }

    @Override
    public void close() throws Exception {
        hibernateManager.close();
    }
}
