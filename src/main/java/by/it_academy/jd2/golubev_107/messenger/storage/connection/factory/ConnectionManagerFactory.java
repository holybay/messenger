package by.it_academy.jd2.golubev_107.messenger.storage.connection.factory;


import by.it_academy.jd2.golubev_107.messenger.platform.IPropertyReader;
import by.it_academy.jd2.golubev_107.messenger.platform.impl.PropertyReader;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.IConnectionManager;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.config.ConnectionManagerConfig;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.impl.ConnectionManager;

import java.io.Closeable;
import java.io.IOException;

public class ConnectionManagerFactory implements Closeable {

    private static final String JDBC_DRIVER = "app.db.driver";
    private static final String DB_URL = "app.db.url";
    private static final String DB_USER = "app.db.user";
    private static final String DB_PASSWORD = "app.db.password";
    private static final ConnectionManagerFactory INSTANCE =
            new ConnectionManagerFactory("/postgres.properties");

    private final IConnectionManager connManager;

    private ConnectionManagerFactory(String dbProperties) {
        IPropertyReader propertyReader = new PropertyReader(dbProperties);
        ConnectionManagerConfig config = new ConnectionManagerConfig();
        config.setDriver(propertyReader.getProperty(JDBC_DRIVER));
        config.setDbUrl(propertyReader.getProperty(DB_URL));
        config.setUser(propertyReader.getProperty(DB_USER));
        config.setPassword(propertyReader.getProperty(DB_PASSWORD));
        connManager = new ConnectionManager(config);
    }

    public static ConnectionManagerFactory getInstance() {
        return INSTANCE;
    }

    public IConnectionManager get() {
        return connManager;
    }

    @Override
    public void close() throws IOException {
        if (connManager != null) {
            connManager.close();
        }
    }
}
