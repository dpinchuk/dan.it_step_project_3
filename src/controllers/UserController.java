package controllers;

import models.UserModel;
import services.UserServiceImpl;

import java.util.List;

/**
 * Class controller for managin UserServiceImpl class
 *
 * @author Pinchuk Dmitry
 */
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * Returns user by login and password
     *
     * @param login    String
     * @param password String
     * @return UserModel
     */
    public UserModel getUserByLoginAndPassword(String login, String password) {
        return this.userService.getUserByLoginAndPassword(login, password);
    }

    /**
     * Returns user by sessio id
     *
     * @param sessionId int
     * @return UserModel
     */
    public UserModel getUserBySessionId(int sessionId) {
        return this.userService.getUserBySessionId(sessionId);
    }

    /**
     * Returns user by login
     *
     * @param login String
     * @return UserModel
     */
    public UserModel getUserByLogin(String login) {
        return this.userService.getUserByLogin(login);
    }

    /**
     * Creates new user
     *
     * @param login       String
     * @param password    String
     * @param userName    String
     * @param userSurname String
     * @return boolean
     */
    public boolean createUser(String login, String password, String userName, String userSurname) {
        return this.userService.createUser(login, password, userName, userSurname);
    }

    /**
     * Write user List to File
     * void
     */
    public void writeUserListToFile() {
        this.userService.writeUserListToFile();
    }

}