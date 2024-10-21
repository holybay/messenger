package by.it_academy.jd2.golubev_107.messenger.storage;

import by.it_academy.jd2.golubev_107.messenger.storage.entity.Message;

import java.util.List;
import java.util.UUID;

public interface IMessageStorage {

    void save(Message message);

    Message readById(Long id);

    List<Message> readAllReceivedByUser(UUID userId);
}
