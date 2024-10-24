package by.it_academy.jd2.golubev_107.messenger.platform;

import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserCreateInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mapper {

    public static UserOutDto toUserOutDto(User user) {
        UserOutDto userOut = new UserOutDto();
        userOut.setId(user.getId());
        userOut.setFullName(user.getFullName());
        userOut.setLogin(user.getLogin());
        userOut.setCreatedAt(user.getCreatedAt());
        userOut.setUpdatedAt(user.getUpdatedAt());
        userOut.setDateOfBirth(user.getDateOfBirth());
        userOut.setRole(user.getRole());
        return userOut;
    }

    public static User toUserEntity(UserCreateInDto dto) {
        User user = User.builder().setId(UUID.randomUUID()).setFullName(dto.getFullName()).setLogin(dto.getLogin()).setPassword(dto.getPassword()).setDateOfBirth(dto.getDateOfBirth()).setCreatedAt(LocalDateTime.now()).setRole(User.ERole.USER).build();
        user.setUpdatedAt(user.getCreatedAt());
        return user;
    }
}
