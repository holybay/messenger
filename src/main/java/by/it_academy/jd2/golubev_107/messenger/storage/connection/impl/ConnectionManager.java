package by.it_academy.jd2.golubev_107.messenger.storage.connection.impl;

import by.it_academy.jd2.golubev_107.messenger.storage.connection.IConnectionManager;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.config.ConnectionManagerConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements IConnectionManager {


    private final DataSource dataSource;

    public ConnectionManager(ConnectionManagerConfig config) {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(config.getDriver());
            cpds.setJdbcUrl(config.getDbUrl());
            cpds.setUser(config.getUser());
            cpds.setPassword(config.getPassword());
            dataSource = cpds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException("The provided connection pool config isn't correct!", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}