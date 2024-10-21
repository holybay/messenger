package by.it_academy.jd2.golubev_107.messenger.service.dto;

import by.it_academy.jd2.golubev_107.messenger.storage.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserOutDto {

    private UUID id;
    private String fullName;
    private String login;
    private LocalDate dateOfBirth;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private User.ERole role;

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
        UserOutDto that = (UserOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(login, that.login) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(createdAt, that.createdAt) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, login, dateOfBirth, updatedAt, createdAt, role);
    }

    @Override
    public String toString() {
        return "UserLoginOutDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", role=" + role +
                '}';
    }
}
