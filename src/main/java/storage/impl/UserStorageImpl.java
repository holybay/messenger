package storage.impl;

import storage.UserStorage;
import storage.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserStorageImpl implements UserStorage {

    private static final UserStorage INSTANCE = new UserStorageImpl();
    private final Map<Long, User> storage = new HashMap<>();
    private final Map<String, User> loginStorage = new HashMap<>();
    private Long idGet = 0L;

    {
        User admin = new User();
        admin.setFullName("Admin User");
        admin.setLogin("Admin");
        admin.setPassword("Admin");
        admin.setDateOfBirth(LocalDate.of(1900, 1, 1));
        admin.setRegDate(LocalDateTime.now());
        admin.setRole(User.ERole.ADMIN);
        Long id = idGet++;
        storage.put(id, admin);
        loginStorage.put(admin.getLogin(), admin);
    }

    private UserStorageImpl() {
    }

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        Long id = idGet++;
        user.setId(id);
        storage.put(id, user);
        loginStorage.put(user.getLogin(), user);
        return storage.get(id);
    }

    @Override
    public User readByLogin(String login) {
        return loginStorage.get(login);
    }
}
