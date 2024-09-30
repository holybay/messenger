package by.it_academy.jd2.golubev_107.messenger.storage;

import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

public interface IUserStorage {

    User save(User user);

    User readByLogin(String login);
}
