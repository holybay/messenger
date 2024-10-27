package by.it_academy.jd2.golubev_107.messenger.service;

import by.it_academy.jd2.golubev_107.messenger.service.dto.message.MessageCreateDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.message.MessageOutDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;

import java.util.List;

public interface IMessageService {

    void create(MessageCreateDto dto);

    List<MessageOutDto> getAll(UserOutDto controllerDto);

    long countAll();
}
