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

    public List<UserModel> getUserList() {
        return this.userService.getUserList();
    }

    public UserModel getUserByLoginAndPassword(String login, String password) {
        return this.userService.getUserByLoginAndPassword(login, password);
    }

    public UserModel getUserBySessionId(int sessionId) {
        return this.userService.getUserBySessionId(sessionId);
    }
}