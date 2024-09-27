package storage.impl;

import storage.UserStorage;
import storage.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserStorageImpl implements UserStorage {

    private static final UserStorage INSTANCE = new UserStorageImpl();
    private final Map<UUID, User> storage = new HashMap<>();
    private final Map<String, User> loginStorage = new HashMap<>();

    {
        User admin = User.builder()
                         .setId(UUID.randomUUID())
                         .setFullName("Admin User")
                         .setLogin("Admin")
                         .setPassword("Admin_admin")
                         .setDateOfBirth(LocalDate.of(1900, 1, 1))
                         .setCreatedAt(LocalDateTime.now())
                         .setRole(User.ERole.ADMIN)
                         .build();
        admin.setUpdatedAt(admin.getCreatedAt());
        storage.put(admin.getId(), admin);
        loginStorage.put(admin.getLogin(), admin);
    }

    private UserStorageImpl() {
    }

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        storage.put(user.getId(), user);
        loginStorage.put(user.getLogin(), user);
        return storage.get(user.getId());
    }

    @Override
    public User readByLogin(String login) {
        return loginStorage.get(login);
    }
}
