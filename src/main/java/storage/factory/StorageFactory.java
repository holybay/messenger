package storage.factory;

import storage.IUserStorage;
import storage.connection.factory.ConnectionManagerFactory;
import storage.impl.UserStorage;

public class StorageFactory {

    private static final StorageFactory INSTANCE = new StorageFactory(ConnectionManagerFactory.getInstance());
    private final IUserStorage userStorage;

    public StorageFactory(ConnectionManagerFactory cmf) {
        this.userStorage = new UserStorage(cmf.get());
    }

    public static StorageFactory getInstance() {
        return INSTANCE;
    }

    public IUserStorage getUserStorage() {
        return userStorage;
    }
}
