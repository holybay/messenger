package storage.impl;

import platform.DBUtil;
import storage.IUserStorage;
import storage.connection.IConnectionManager;
import storage.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class UserStorage implements IUserStorage {

    private static final String INSERT_USER_QUERY = """
            INSERT INTO app.user (id, full_name, login, password, date_of_birth, updated_at, created_at, role_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);""";
    private static final String SELECT_USER_BY_LOGIN_QUERY = """
            SELECT id, full_name, login, password, date_of_birth, updated_at, created_at, role_id
            FROM app.user
            WHERE login = ?;""";
    private static final String SELECT_ROLE_ID_BY_ROLE_QUERY = "SELECT id FROM app.role WHERE name = ?";
    private static final String SELECT_ROLE_BY_ID_QUERY = "SELECT name FROM app.role WHERE id = ?";
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
    public User readByLogin(String login) {
        try (Connection conn = connectionManager.getConnection()) {
            try (PreparedStatement selectUser = conn.prepareStatement(SELECT_USER_BY_LOGIN_QUERY)) {
                selectUser.setString(1, login);
                try (ResultSet rs = selectUser.executeQuery()) {
                    while (rs.next()) {
                        User.ERole role = getUserRole(rs.getLong("role_id"), conn);
                        return User.builder()
                                   .setId(rs.getObject("id", UUID.class))
                                   .setFullName(rs.getString("full_name"))
                                   .setLogin(rs.getString("login"))
                                   .setPassword(rs.getString("password"))
                                   .setDateOfBirth(rs.getDate("date_of_birth").toLocalDate())
                                   .setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                                   .setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                                   .setRole(role)
                                   .build();
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read a user by login: " + login, e);
        }
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
            insertUserStmt.setObject(8, getUserRoleId(user.getRole(), conn));
            int rowsUpdated = insertUserStmt.executeUpdate();

            insertUserStmt.clearParameters();
            if (rowsUpdated != 1) {
                DBUtil.transactionRollback(conn);
                throw new IllegalStateException("Created unexpected amount of rows: " + rowsUpdated);
            }
        }
    }

    private Long getUserRoleId(User.ERole role, Connection conn) throws SQLException {
        try (PreparedStatement selectRoleId = conn.prepareStatement(SELECT_ROLE_ID_BY_ROLE_QUERY)) {
            selectRoleId.setString(1, role.name());
            try (ResultSet rs = selectRoleId.executeQuery()) {
                selectRoleId.clearParameters();
                Long id = null;
                if (rs.next()) {
                    id = rs.getLong("id");
                }
                if (id == null) {
                    DBUtil.transactionRollback(conn);
                    throw new IllegalStateException("There is no such role within the system: " + role);
                }
                return id;
            }
        }
    }

    private User.ERole getUserRole(Long id, Connection conn) throws SQLException {
        try (PreparedStatement selectRoleId = conn.prepareStatement(SELECT_ROLE_BY_ID_QUERY)) {
            selectRoleId.setLong(1, id);
            try (ResultSet rs = selectRoleId.executeQuery()) {
                selectRoleId.clearParameters();
                User.ERole role = null;
                if (rs.next()) {
                    role = User.ERole.valueOf(rs.getString(1));
                }
                if (role == null) {
                    throw new IllegalStateException("There is no role within the system with such id: " + id);
                }
                return role;
            }
        }
    }

}
