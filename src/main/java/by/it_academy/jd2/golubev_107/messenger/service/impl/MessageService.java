package by.it_academy.jd2.golubev_107.messenger.service.impl;

import by.it_academy.jd2.golubev_107.messenger.platform.Mapper;
import by.it_academy.jd2.golubev_107.messenger.service.IMessageService;
import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.message.MessageCreateDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.message.MessageOutDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import by.it_academy.jd2.golubev_107.messenger.storage.IMessageStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.Message;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageStorage messageStorage;
    private final IUserService userService;

    public MessageService(IMessageStorage messageStorage, IUserService userService) {
        this.messageStorage = messageStorage;
        this.userService = userService;
    }

    @Override
    public void create(MessageCreateDto dto) {
        User from = userService.getByLogin(dto.getFrom().getLogin());
        User to = userService.getByLogin(dto.getTo());
        Message message = Mapper.toMessageEntity(dto, from, to);
        messageStorage.save(message);
    }

    @Override
    public List<MessageOutDto> getAll(UserOutDto controllerDto) {
        if (controllerDto == null) {
            throw new IllegalArgumentException("You aren't logged in!");
        }
        List<Message> messages = messageStorage.readAllReceivedByUser(controllerDto.getId());
        return messages.stream()
                       .map(Mapper::toMessageOutDto)
                       .toList();
    }

}
