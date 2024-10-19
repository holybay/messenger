package by.it_academy.jd2.golubev_107.messenger.storage.factory;

import by.it_academy.jd2.golubev_107.messenger.storage.IUserStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.HibernateManager;
import by.it_academy.jd2.golubev_107.messenger.storage.impl.UserStorageHibernate;

public class StorageFactory implements AutoCloseable {

    private static final StorageFactory INSTANCE = new StorageFactory();
    private final IUserStorage userStorage;
    private final HibernateManager hibernateManager;

    public StorageFactory() {
        hibernateManager = new HibernateManager("unit");
        this.userStorage = new UserStorageHibernate(hibernateManager);
    }

    public static StorageFactory getInstance() {
        return INSTANCE;
    }

    public IUserStorage getUserStorage() {
        return userStorage;
    }

    @Override
    public void close() throws Exception {
        hibernateManager.close();
    }
}
