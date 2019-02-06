package services;

import models.UserModel;

/**
 * Interface for UserServiceImpl
 *
 * @author Pinchuk Dmitry
 */
public interface UserService {

    /**
     * @param id int
     * @return boolean
     */
    boolean isUserExist(int id);

    /**
     * @param login       String
     * @param password    String
     * @param userName    String
     * @param userSurname String
     * @return boolean
     */
    boolean createUser(String login, String password, String userName, String userSurname);

    /**
     * @param login    String
     * @param password String
     * @return UserModel
     */
    UserModel getUserByLoginAndPassword(String login, String password);

    /**
     * @param sessionId int
     * @return UserModel
     */
    UserModel getUserBySessionId(int sessionId);

    /**
     * @param login String
     * @return UserModel
     */
    UserModel getUserByLogin(String login);

    /**
     * void
     */
    void writeUserListToFile();

}