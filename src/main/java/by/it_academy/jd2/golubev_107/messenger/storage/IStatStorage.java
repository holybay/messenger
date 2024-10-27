package by.it_academy.jd2.golubev_107.messenger.storage;

import by.it_academy.jd2.golubev_107.messenger.storage.entity.StatCounter;

public interface IStatStorage {

    StatCounter readByName(String name);

    void update(StatCounter statCounter);
}
