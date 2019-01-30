package services;

import models.UserModel;

/**
 * Interface for UserServiceImpl
 *
 * @author Pinchuk Dmitry
 */
public interface UserService {

    /**
     * Returns true/false if user exist
     *
     * @param id int
     * @return boolean
     */
    boolean isUserExist(int id);

    /**
     * Creates new user
     *
     * @param login       String
     * @param password    String
     * @param userName    String
     * @param userSurname String
     * @return UserModel
     */
    UserModel createUser(String login, String password, String userName, String userSurname);

    UserModel getUserByLoginAndPassword(String login, String password);

    UserModel getUserBySessionId(int sessionId);

    UserModel getUserByLogin(String login);

}