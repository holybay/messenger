package by.it_academy.jd2.golubev_107.messenger.storage.factory;

import by.it_academy.jd2.golubev_107.messenger.storage.IUserStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.factory.ConnectionManagerFactory;
import by.it_academy.jd2.golubev_107.messenger.storage.impl.UserStorage;

import java.io.Closeable;
import java.io.IOException;

public class StorageFactory implements Closeable {

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

    @Override
    public void close() throws IOException {
        ConnectionManagerFactory.getInstance().close();
    }
}
