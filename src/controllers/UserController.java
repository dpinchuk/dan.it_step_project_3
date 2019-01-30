package controllers;

import models.UserModel;
import services.UserServiceImpl;

import java.util.List;

/**
 * Class controller for managin UserServiceImpl class
 * @author Pinchuk Dmitry
 */
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    public UserModel getUserByLoginAndPassword(String login, String password) {
        return this.userService.getUserByLoginAndPassword(login, password);
    }

    public UserModel getUserBySessionId(int sessionId) {
        return this.userService.getUserBySessionId(sessionId);
    }

    public UserModel getUserByLogin(String login) {
        return this.userService.getUserByLogin(login);
    }

    public void createUser(String login, String password, String userName, String userSurname) {
        this.userService.createUser(login, password, userName, userSurname);
    }
}