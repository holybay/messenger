package by.it_academy.jd2.golubev_107.messenger.service.dto.message;

import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;

import java.util.Objects;

public class MessageCreateDto {

    private UserOutDto from;
    private String to;
    private String text;

    public UserOutDto getFrom() {
        return from;
    }

    public void setFrom(UserOutDto from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageCreateDto that = (MessageCreateDto) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, text);
    }

    @Override
    public String toString() {
        return "MessageCreateDto{" +
                "from=" + from +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
