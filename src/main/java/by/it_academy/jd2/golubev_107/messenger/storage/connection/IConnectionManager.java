package by.it_academy.jd2.golubev_107.messenger.storage.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionManager {

    Connection getConnection() throws SQLException;
}