package controllers;

import services.UserServiceImpl;

public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    public UserServiceImpl getUserService() {
        return userService;
    }

}