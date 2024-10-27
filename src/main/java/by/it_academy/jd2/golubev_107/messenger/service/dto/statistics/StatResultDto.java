package by.it_academy.jd2.golubev_107.messenger.service.dto.statistics;

import java.util.Objects;

public class StatResultDto {

    private final long activeUsers;
    private final long totalUsers;
    private final long totalMessagesSent;

    private StatResultDto(long activeUsers, long totalUsers, long totalMessagesSent) {
        this.activeUsers = activeUsers;
        this.totalUsers = totalUsers;
        this.totalMessagesSent = totalMessagesSent;
    }

    public static StatResultDtoBuilder builder() {
        return new StatResultDtoBuilder();
    }

    public long getActiveUsers() {
        return activeUsers;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalMessagesSent() {
        return totalMessagesSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatResultDto that = (StatResultDto) o;
        return activeUsers == that.activeUsers && totalUsers == that.totalUsers && totalMessagesSent == that.totalMessagesSent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeUsers, totalUsers, totalMessagesSent);
    }

    @Override
    public String toString() {
        return "StatResultDto{" +
                "activeUsers=" + activeUsers +
                ", totalUsers=" + totalUsers +
                ", totalMessagesSent=" + totalMessagesSent +
                '}';
    }

    public static class StatResultDtoBuilder {
        private long activeUsers;
        private long totalUsers;
        private long totalMessagesSent;

        private StatResultDtoBuilder() {
        }

        public StatResultDtoBuilder setActiveUsers(long activeUsers) {
            this.activeUsers = activeUsers;
            return this;
        }

        public StatResultDtoBuilder setTotalUsers(long totalUsers) {
            this.totalUsers = totalUsers;
            return this;
        }

        public StatResultDtoBuilder setTotalMessagesSent(long totalMessagesSent) {
            this.totalMessagesSent = totalMessagesSent;
            return this;
        }

        public StatResultDto build() {
            return new StatResultDto(activeUsers, totalUsers, totalMessagesSent);
        }
    }
}
