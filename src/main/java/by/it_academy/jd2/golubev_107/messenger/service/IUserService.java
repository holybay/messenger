package by.it_academy.jd2.golubev_107.messenger.service;

import by.it_academy.jd2.golubev_107.messenger.service.dto.UserCreateInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.UserLoginInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.UserLoginOutDto;

public interface IUserService {

    boolean create(UserCreateInDto dto);

    UserLoginOutDto login(UserLoginInDto inDto);
}
