package storage;

import storage.entity.User;

public interface UserStorage {

    User save(User user);

    User readByLogin(String login);
}
