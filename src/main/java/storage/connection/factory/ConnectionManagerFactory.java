package storage.connection.factory;


import platform.IPropertyReader;
import platform.impl.PropertyReader;
import storage.connection.IConnectionManager;
import storage.connection.config.ConnectionManagerConfig;
import storage.connection.impl.ConnectionManager;

public class ConnectionManagerFactory {

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

}
