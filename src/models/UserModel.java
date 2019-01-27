package models;

import java.io.Serializable;
import java.util.Objects;

public class UserModel implements Serializable {

    private int id;
    private String login;
    private String password;

    public UserModel(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id +
                "\t\t\t\t" + login +
                "\t\t\t\t" + password +
                "\t\t\t\t" + this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(login, userModel.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

}