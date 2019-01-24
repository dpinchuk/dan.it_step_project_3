package controllers;

import models.UserModel;
import services.UserService;

import java.util.List;

public class UserController {

    private UserService flightService = new UserService();

    public List<UserModel> getUserList() {
        return this.flightService.getUserList();
    }

}