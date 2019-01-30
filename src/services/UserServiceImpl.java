package services;

import dao.UserDAOImpl;
import models.UserModel;

import java.util.List;

/**
 * Service class extends MainService implements UserService
 *
 * @author Pinchuk Dmitry
 */
public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

    /**
     * Returns true/false if user exist
     *
     * @param id int
     * @return boolean
     */
    @Override
    public boolean isUserExist(int id) {
        return this.userDAO.getUserById(id) != null;
    }

    @Override
    public UserModel createUser(String login, String password, String userName, String userSurname) {
        return this.userDAO.createUser(login, password, userName, userSurname);
    }

    @Override
    public UserModel getUserByLoginAndPassword(String login, String password) {
        return this.userDAO.getUserByLoginAndPassword(login, password);
    }

    @Override
    public UserModel getUserBySessionId(int sessionId) {
        return this.userDAO.getUserBySessionId(sessionId);
    }

    @Override
    public UserModel getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }
}