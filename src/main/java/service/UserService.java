package service;

import service.dto.UserCreateInDto;
import service.dto.UserLoginInDto;
import service.dto.UserLoginOutDto;

public interface UserService {

    boolean create(UserCreateInDto dto);

    UserLoginOutDto login(UserLoginInDto inDto);
}
