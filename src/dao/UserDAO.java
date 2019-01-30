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

}