package storage.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private Long id;
    private String fullName;
    private String login;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDateTime regDate;
    private ERole role;

    public enum ERole {
        USER,
        ADMIN
    }

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

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(fullName, user.fullName) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(regDate, user.regDate) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, login, password, dateOfBirth, regDate, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", regDate=" + regDate +
                ", role=" + role +
                '}';
    }
}
