package storage.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID id;
    private String fullName;
    private String login;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private ERole role;

    private User(UUID id, String fullName, String login, String password, LocalDate dateOfBirth,
                 LocalDateTime updatedAt, LocalDateTime createdAt, ERole role) {
        this.id = id;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.role = role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        return Objects.equals(id, user.id) && Objects.equals(fullName, user.fullName) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(createdAt, user.createdAt) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, login, password, dateOfBirth, updatedAt, createdAt, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", role=" + role +
                '}';
    }

    public enum ERole {
        USER,
        ADMIN
    }

    public static class UserBuilder {
        private UUID id;
        private String fullName;
        private String login;
        private String password;
        private LocalDate dateOfBirth;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;
        private ERole role;

        private UserBuilder() {
        }

        public UserBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder setRole(ERole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(id, fullName, login, password, dateOfBirth, updatedAt, createdAt, role);
        }
    }

}
