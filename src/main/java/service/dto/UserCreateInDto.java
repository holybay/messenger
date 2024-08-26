package service.dto;

import java.time.LocalDate;
import java.util.Objects;

public class UserCreateInDto {

    private String fullName;
    private String login;
    private String password;
    private LocalDate dateOfBirth;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateInDto userInDto = (UserCreateInDto) o;
        return Objects.equals(fullName, userInDto.fullName) && Objects.equals(login, userInDto.login) && Objects.equals(password, userInDto.password) && Objects.equals(dateOfBirth, userInDto.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, login, password, dateOfBirth);
    }

    @Override
    public String toString() {
        return "UserInDto{" +
                "fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
