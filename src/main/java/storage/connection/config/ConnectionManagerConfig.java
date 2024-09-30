package storage.connection.config;

import java.util.Objects;

public class ConnectionManagerConfig {

    private String driver;
    private String dbUrl;
    private String user;
    private String password;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionManagerConfig that = (ConnectionManagerConfig) o;
        return Objects.equals(driver, that.driver) && Objects.equals(dbUrl, that.dbUrl) && Objects.equals(user, that.user) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver, dbUrl, user, password);
    }

    @Override
    public String toString() {
        return "ConnectionManagerConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + dbUrl + '\'' +
                ", User='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
