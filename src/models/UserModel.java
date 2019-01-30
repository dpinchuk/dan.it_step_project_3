package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model class of users
 *
 * @author Pinchuk Dmitry
 */
public class UserModel implements Serializable {

    private int id;
    private String login;
    private String password;
    private String userName;
    private String userSurname;

    public UserModel(int id, String login, String password, String userName, String userSurname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
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

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    @Override
    public String toString() {
        return "\t\t" + id +
                "\t\t" + userName +
                "\t\t" + userSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userSurname);
    }
}