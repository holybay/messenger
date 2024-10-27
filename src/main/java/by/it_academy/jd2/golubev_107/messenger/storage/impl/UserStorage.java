package by.it_academy.jd2.golubev_107.messenger.storage.impl;

import by.it_academy.jd2.golubev_107.messenger.platform.DBUtil;
import by.it_academy.jd2.golubev_107.messenger.storage.IUserStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.connection.IConnectionManager;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class UserStorage implements IUserStorage {

    private static final String INSERT_USER_QUERY = """
            INSERT INTO app.users (id, full_name, login, password, date_of_birth, updated_at, created_at, role)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);""";
    private static final String UPDATE_USER_QUERY = """
            UPDATE app.users (id, full_name, login, password, date_of_birth, updated_at, created_at, role)
            SET full_name = ?, login = ?, password = ?, date_of_birth = ?, updated_at = ?, role = ?);""";
    private static final String SELECT_USER_BY_LOGIN_QUERY = """
            SELECT id, full_name, login, password, date_of_birth, updated_at, created_at, role
            FROM app.users
            WHERE login = ?;""";
    private static final String SELECT_USER_BY_ID_QUERY = """
            SELECT id, full_name, login, password, date_of_birth, updated_at, created_at, role
            FROM app.users
            WHERE id = ?;""";
    private static final String SELECT_COUNT_ALL_QUERY = "SELECT count(id) FROM app.users;";
    private final IConnectionManager connectionManager;

    public UserStorage(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public User save(User user) {
        Connection conn = null;
        try {
            conn = connectionManager.getConnection();
            conn.setAutoCommit(false);
            DBUtil.transactionBegin(conn);
            createUser(user, conn);

            conn.commit();
        } catch (SQLException | RuntimeException e) {
            DBUtil.transactionRollback(conn);
            throw new RuntimeException("Failed to create a user: " + user, e);
        } finally {
            DBUtil.connectionClose(conn);
        }
        return readByLogin(user.getLogin());
    }

    @Override
    public User update(User user) {
        Connection conn = null;
        try {
            conn = connectionManager.getConnection();
            conn.setAutoCommit(false);
            DBUtil.transactionBegin(conn);
            updateUser(user, conn);

            conn.commit();
        } catch (SQLException | RuntimeException e) {
            DBUtil.transactionRollback(conn);
            throw new RuntimeException("Failed to update the user: " + user, e);
        } finally {
            DBUtil.connectionClose(conn);
        }
        return readByLogin(user.getLogin());
    }

    @Override
    public User readById(UUID id) {
        try (Connection conn = connectionManager.getConnection()) {
            try (PreparedStatement selectUser = conn.prepareStatement(SELECT_USER_BY_ID_QUERY)) {
                selectUser.setObject(1, id);
                try (ResultSet rs = selectUser.executeQuery()) {
                    if (rs.next()) {
                        return mapper(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read a user by id: " + id, e);
        }
        return null;
    }

    @Override
    public User readByLogin(String login) {
        try (Connection conn = connectionManager.getConnection()) {
            try (PreparedStatement selectUser = conn.prepareStatement(SELECT_USER_BY_LOGIN_QUERY)) {
                selectUser.setString(1, login);
                try (ResultSet rs = selectUser.executeQuery()) {
                    if (rs.next()) {
                        return mapper(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read a user by login: " + login, e);
        }
        return null;
    }

    @Override
    public long countAll() {
        long userCount = 0L;
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement countAll = conn.prepareStatement(SELECT_COUNT_ALL_QUERY)) {
            try (ResultSet rs = countAll.executeQuery()) {
                if (rs.next()) {
                    userCount = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count all users!", e);
        }
        return userCount;
    }

    private User mapper(ResultSet rs) throws SQLException {
        return User.builder().setId(rs.getObject("id", UUID.class)).setFullName(rs.getString("full_name")).setLogin(rs.getString("login")).setPassword(rs.getString("password")).setDateOfBirth(rs.getDate("date_of_birth").toLocalDate()).setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime()).setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime()).setRole(User.ERole.valueOf(rs.getString("role"))).build();
    }

    private void createUser(User user, Connection conn) throws SQLException {
        try (PreparedStatement insertUserStmt = conn.prepareStatement(INSERT_USER_QUERY)) {
            insertUserStmt.setObject(1, user.getId());
            insertUserStmt.setString(2, user.getFullName());
            insertUserStmt.setString(3, user.getLogin());
            insertUserStmt.setString(4, user.getPassword());
            insertUserStmt.setDate(5, Date.valueOf(user.getDateOfBirth()));
            insertUserStmt.setTimestamp(6, Timestamp.valueOf(user.getUpdatedAt()));
            insertUserStmt.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            insertUserStmt.setString(8, user.getRole().name());
            int rowsUpdated = insertUserStmt.executeUpdate();

            insertUserStmt.clearParameters();
            if (rowsUpdated != 1) {
                DBUtil.transactionRollback(conn);
                throw new IllegalStateException("Created unexpected amount of rows: " + rowsUpdated);
            }
        }
    }

    private void updateUser(User user, Connection conn) throws SQLException {
        try (PreparedStatement insertUserStmt = conn.prepareStatement(UPDATE_USER_QUERY)) {
            insertUserStmt.setString(1, user.getFullName());
            insertUserStmt.setString(2, user.getLogin());
            insertUserStmt.setString(3, user.getPassword());
            insertUserStmt.setDate(4, Date.valueOf(user.getDateOfBirth()));
            insertUserStmt.setTimestamp(5, Timestamp.valueOf(user.getUpdatedAt()));
            insertUserStmt.setString(6, user.getRole().name());
            int rowsUpdated = insertUserStmt.executeUpdate();

            insertUserStmt.clearParameters();
            if (rowsUpdated != 1) {
                DBUtil.transactionRollback(conn);
                throw new IllegalStateException("Updated unexpected amount of rows: " + rowsUpdated);
            }
        }
    }
}
