package by.it_academy.jd2.golubev_107.messenger.service;

import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserCreateInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserLoginInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.util.UUID;

public interface IUserService {

    boolean create(UserCreateInDto dto);

    UserOutDto login(UserLoginInDto inDto);

    UserOutDto getById(UUID id);

    User getByLogin(String login);
}
