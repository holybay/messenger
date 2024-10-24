package by.it_academy.jd2.golubev_107.messenger.storage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesIdGen")
    @SequenceGenerator(name = "messagesIdGen", sequenceName = "messages_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_from_id")
    private User from;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_to_id")
    private User to;

    @Column(name = "delivered_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deliveredAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Message() {
    }

    private Message(Long id, String text, User from, User to, LocalDateTime deliveredAt, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.from = from;
        this.to = to;
        this.deliveredAt = deliveredAt;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
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

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
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
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(text, message.text) && Objects.equals(from, message.from) && Objects.equals(to, message.to) && Objects.equals(deliveredAt, message.deliveredAt) && Objects.equals(updatedAt, message.updatedAt) && Objects.equals(createdAt, message.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, from, to, deliveredAt, updatedAt, createdAt);
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", text='" + text + '\'' + ", from=" + from + ", to=" + to + ", deliveredAt=" + deliveredAt + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + '}';
    }

    public static class MessageBuilder {

        private Long id;
        private String text;
        private User from;
        private User to;
        private LocalDateTime deliveredAt;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;

        private MessageBuilder() {
        }

        public MessageBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public MessageBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public MessageBuilder setFrom(User from) {
            this.from = from;
            return this;
        }

        public MessageBuilder setTo(User to) {
            this.to = to;
            return this;
        }

        public MessageBuilder setDeliveredAt(LocalDateTime deliveredAt) {
            this.deliveredAt = deliveredAt;
            return this;
        }

        public MessageBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public MessageBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Message build() {
            return new Message(id, text, from, to, deliveredAt, updatedAt, createdAt);
        }
    }
}
