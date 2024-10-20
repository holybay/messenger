package by.it_academy.jd2.golubev_107.messenger.storage.connection.impl;

import by.it_academy.jd2.golubev_107.messenger.storage.connection.IConnectionManager;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.config.ConnectionManagerConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements IConnectionManager {


    private final ComboPooledDataSource dataSource;

    public ConnectionManager(ConnectionManagerConfig config) {
        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(config.getDriver());
            dataSource.setJdbcUrl(config.getDbUrl());
            dataSource.setUser(config.getUser());
            dataSource.setPassword(config.getPassword());
        } catch (PropertyVetoException e) {
            throw new RuntimeException("The provided connection pool config isn't correct!", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() throws IOException {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
