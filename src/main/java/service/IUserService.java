package service;

import service.dto.UserCreateInDto;
import service.dto.UserLoginInDto;
import service.dto.UserLoginOutDto;

public interface IUserService {

    boolean create(UserCreateInDto dto);

    UserLoginOutDto login(UserLoginInDto inDto);
}
