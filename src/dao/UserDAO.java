package dao;

import models.UserModel;

/**
 * Interface for UserDAOImpl
 *
 * @author Pinchuk Dmitry
 */
public interface UserDAO {

    /**
     * @param id int
     * @return UserModel
     */
    UserModel getUserById(int id); //возвращает user по [id]

    /**
     * @param login    String
     * @param password String
     * @return UserModel
     */
    UserModel getUserByLoginAndPassword(String login, String password); //возвращает user по [login, password]

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
     * @param login       String
     * @param password    String
     * @param userName    String
     * @param userSurname String
     * @return UserModel
     */
    UserModel createUser(String login, String password, String userName, String userSurname);

    /**
     * void
     */
    void writeUserListToFile();

}