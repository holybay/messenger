package storage;

import storage.entity.User;

public interface IUserStorage {

    User save(User user);

    User readByLogin(String login);
}
