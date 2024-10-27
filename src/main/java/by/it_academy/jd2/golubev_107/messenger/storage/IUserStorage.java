package by.it_academy.jd2.golubev_107.messenger.storage;

import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.util.UUID;

public interface IUserStorage {

    User save(User user);

    User update(User user);

    User readById(UUID id);

    User readByLogin(String login);

    long countAll();
}
