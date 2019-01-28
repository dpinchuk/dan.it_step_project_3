package dao;

import models.UserModel;

import java.util.List;

/**
 * Interface for UserDAOImpl
 * @author Pinchuk Dmitry
 */
public interface UserDAO {

    /**
     *
     * @param id
     * @return
     */
    UserModel getUserById(int id);

    UserModel getUserByLogin(String login);

    UserModel getUserByLoginAndPassword(String[] userLoginAndPassword);

}