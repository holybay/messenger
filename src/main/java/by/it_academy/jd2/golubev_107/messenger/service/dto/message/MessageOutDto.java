package by.it_academy.jd2.golubev_107.messenger.service.dto.message;

import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageOutDto {

    private Long id;
    private String text;
    private UserOutDto from;
    private UserOutDto to;
    private LocalDateTime deliveredAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private MessageOutDto(Long id, String text, UserOutDto from, UserOutDto to, LocalDateTime deliveredAt,
                          LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.from = from;
        this.to = to;
        this.deliveredAt = deliveredAt;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static MessageOutDtoBuilder builder() {
        return new MessageOutDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserOutDto getFrom() {
        return from;
    }

    public void setFrom(UserOutDto from) {
        this.from = from;
    }

    public UserOutDto getTo() {
        return to;
    }

    public void setTo(UserOutDto to) {
        this.to = to;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageOutDto that = (MessageOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(deliveredAt, that.deliveredAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, from, to, deliveredAt, updatedAt, createdAt);
    }

    @Override
    public String toString() {
        return "MessageOutDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", deliveredAt=" + deliveredAt +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class MessageOutDtoBuilder {

        private Long id;
        private String text;
        private UserOutDto from;
        private UserOutDto to;
        private LocalDateTime deliveredAt;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;

        private MessageOutDtoBuilder() {
        }

        public MessageOutDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public MessageOutDtoBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public MessageOutDtoBuilder setFrom(UserOutDto from) {
            this.from = from;
            return this;
        }

        public MessageOutDtoBuilder setTo(UserOutDto to) {
            this.to = to;
            return this;
        }

        public MessageOutDtoBuilder setDeliveredAt(LocalDateTime deliveredAt) {
            this.deliveredAt = deliveredAt;
            return this;
        }

        public MessageOutDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public MessageOutDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MessageOutDto build() {
            return new MessageOutDto(id, text, from, to, deliveredAt, updatedAt, createdAt);
        }
    }
}
