package service.dto;

import storage.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserLoginOutDto {

    private Long id;
    private String fullName;
    private String login;
    private LocalDate dateOfBirth;
    private LocalDateTime regDate;
    private User.ERole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public User.ERole getRole() {
        return role;
    }

    public void setRole(User.ERole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginOutDto that = (UserLoginOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(login, that.login) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(regDate, that.regDate) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, login, dateOfBirth, regDate, role);
    }

    @Override
    public String toString() {
        return "UserLoginOutDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", regDate=" + regDate +
                ", role=" + role +
                '}';
    }
}
