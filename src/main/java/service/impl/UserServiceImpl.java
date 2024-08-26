package service.impl;

import service.UserService;
import service.dto.UserCreateInDto;
import service.dto.UserLoginInDto;
import service.dto.UserLoginOutDto;
import storage.UserStorage;
import storage.entity.User;
import storage.impl.UserStorageImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();
    private static final int FULL_NAME_PARTS_COUNT = 2;
    private final UserStorage storage = UserStorageImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }


    @Override
    public boolean create(UserCreateInDto dto) {
        validateCreate(dto);
        User toSave = toEntity(dto);
        User saved = storage.save(toSave);
        return saved.getId() != null;
    }

    @Override
    public UserLoginOutDto login(UserLoginInDto inDto) {
        validateLogin(inDto);
        User userOut = storage.readByLogin(inDto.getLogin());
        return toUserLoginOut(userOut);
    }

    private User toEntity(UserCreateInDto dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setRegDate(LocalDateTime.now());
        user.setRole(User.ERole.USER);
        return user;
    }

    private UserLoginOutDto toUserLoginOut(User user) {
        UserLoginOutDto userOut = new UserLoginOutDto();
        userOut.setId(user.getId());
        userOut.setFullName(user.getFullName());
        userOut.setLogin(user.getLogin());
        userOut.setRegDate(user.getRegDate());
        userOut.setDateOfBirth(user.getDateOfBirth());
        userOut.setRole(user.getRole());
        return userOut;
    }

    private void validateLogin(UserLoginInDto loginInDto) {
        List<String> errors = new ArrayList<>();
        String login = loginInDto.getLogin();
        loginCheck(login, errors);
        String password = loginInDto.getPassword();
        passwordCheck(password, errors);
        inputErrorsCheck(errors);
        if (!isValidUser(loginInDto)) {
            throw new IllegalArgumentException("User with such name and password doesn't exist!" + login);
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
