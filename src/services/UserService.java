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



}