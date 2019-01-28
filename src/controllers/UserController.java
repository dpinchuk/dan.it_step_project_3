package controllers;

import services.UserServiceImpl;

/**
 * Class controller for managin UserServiceImpl class
 * @author Pinchuk Dmitry
 */
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * Getter
     * @return this.userService
     */
    public UserServiceImpl getUserService() {
        return this.userService;
    }

}