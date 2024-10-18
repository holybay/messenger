package by.it_academy.jd2.golubev_107.messenger.storage.connection;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionManager extends Closeable {

    Connection getConnection() throws SQLException;
}
