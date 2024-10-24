package by.it_academy.jd2.golubev_107.messenger.service.impl;

import by.it_academy.jd2.golubev_107.messenger.platform.Mapper;
import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserCreateInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserLoginInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import by.it_academy.jd2.golubev_107.messenger.storage.IUserStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {

    private static final int FULL_NAME_PARTS_COUNT = 2;
    private final IUserStorage storage;

    public UserService(IUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean create(UserCreateInDto dto) {
        validateCreate(dto);
        User toSave = Mapper.toUserEntity(dto);
        User saved = storage.save(toSave);
        return saved.getId() != null;
    }

    @Override
    public UserOutDto login(UserLoginInDto inDto) {
        validateLogin(inDto);
        User userOut = storage.readByLogin(inDto.getLogin());
        return Mapper.toUserOutDto(userOut);
    }

    @Override
    public UserOutDto getById(UUID id) {
        if (id == null) {
            throw new RuntimeException("User id can't be blank!");
        }
        User user = storage.readById(id);
        if (user == null) {
            throw new RuntimeException("User with such id doesn't exist: " + id);
        }
        return Mapper.toUserOutDto(user);
    }

    private void validateLogin(UserLoginInDto loginInDto) {
        List<String> errors = new ArrayList<>();
        String login = loginInDto.getLogin();
        loginCheck(login, errors);
        String password = loginInDto.getPassword();
        passwordCheck(password, errors);
        inputErrorsCheck(errors);
        if (!isValidUser(loginInDto)) {
            throw new IllegalArgumentException(String.format("User \"%s\" doesn't exist " + "or you've entered incorrect password!", login));
        }
    }

    private boolean isValidUser(UserLoginInDto loginInDto) {
        User user = storage.readByLogin(loginInDto.getLogin());
        return user != null && user.getPassword().equalsIgnoreCase(loginInDto.getPassword());
    }

    private void validateCreate(UserCreateInDto dto) {
        List<String> errors = new ArrayList<>();
        String fullName = dto.getFullName();
        if (fullName == null || fullName.isBlank()) {
            errors.add("Full name can't be blank");
        }
        if (fullName != null && fullName.split(" ").length != FULL_NAME_PARTS_COUNT) {
            errors.add("Last name and first name must be divided by the space.");
        }
        String login = dto.getLogin();
        loginCheck(login, errors);

        String password = dto.getPassword();
        passwordCheck(password, errors);
        LocalDate dob = dto.getDateOfBirth();
        if (dob == null) {
            errors.add("Date of birth can't be blank");
        }
        if (dob != null && LocalDate.now().isBefore(dob)) {
            errors.add("Incorrect date of birth! Date of birth can't be greater than today!");
        }
        inputErrorsCheck(errors);
    }

    private void inputErrorsCheck(List<String> errors) {
        if (!errors.isEmpty()) {
            throw new RuntimeException(String.join("\n", errors));
        }
    }

    private void passwordCheck(String password, List<String> errors) {
        if (password == null || password.isBlank()) {
            errors.add("Password can't be blank");
        }
        if (password != null && password.length() < 10) {
            errors.add("Password length must be at least 10 symbols");
        }
    }

    private void loginCheck(String login, List<String> errors) {
        if (login == null || login.isBlank()) {
            errors.add("Login can't be blank");
        }
        if (login != null && !login.matches("[A-Za-z]+\\w*")) {
            errors.add("Login should start from a char! You can use Latin chars, digits and \"_\" only!");
        }
        if (login != null && login.length() < 5) {
            errors.add("Login should be at least 5 symbols long");
        }
    }

}
