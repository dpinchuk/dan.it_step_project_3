package dao;

import models.UserModel;

/**
 * Interface for UserDAOImpl
 *
 * @author Pinchuk Dmitry
 */
public interface UserDAO {

    /**
     * Returns user by [id]
     *
     * @param id int
     * @return
     */
    UserModel getUserById(int id); //возвращает user по [id]

    /**
     * Returns user by [login, password]
     *
     * @param login
     * @param password
     * @return
     */
    UserModel getUserByLoginAndPassword(String login, String password); //возвращает user по [login, password]

    /**
     * Returns user by [sessionId]
     *
     * @param sessionId int
     * @return UserModel
     */
    UserModel getUserBySessionId(int sessionId);

    /**
     * Returns user by login [Only for check during registration!]
     * @param login String
     * @return UserModel
     */
    UserModel getUserByLogin(String login);

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
}