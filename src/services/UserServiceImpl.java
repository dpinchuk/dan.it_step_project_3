package services;

import dao.UserDAOImpl;
import models.UserModel;

import static utils.Constants.*;

/**
 * Service class extends MainService implements UserService
 *
 * @author Pinchuk Dmitry
 */
public class UserServiceImpl extends MainService implements UserService {

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

    /**
     * Creates new user by data
     *
     * @param login       String
     * @param password    String
     * @param userName    String
     * @param userSurname String
     * @return boolean
     */
    @Override
    public boolean createUser(String login, String password, String userName, String userSurname) {
        if (!login.equals("") && !password.equals("") && !userName.equals("") && !userSurname.equals("")) {
            if (this.userDAO.getUserByLogin(login) != null) {
                getException(ERROR_REGISTRATION_USER_IS_ALREADY_REGISTERED, ERROR_REGISTRATION_USER_IS_ALREADY_REGISTERED +
                        "[login: " + login + "]");
                return false;
            }
            UserModel newUser =  this.userDAO.createUser(login, password, userName, userSurname);
            if (newUser != null) {
                return true;
            } else {
                getException(ERROR_REGISTRATION_USER_HAS_NOT_BEEN_CREATED, ERROR_REGISTRATION_USER_HAS_NOT_BEEN_CREATED +
                        "[login: " + login + ", password: " + password + ", username: " + userName + ", userSurname: " + userSurname + "]");
                return false;
            }
        }
        getException(ERROR_AUTHORIZATION_USER_ENTERED_INVALID_DATA, ERROR_AUTHORIZATION_USER_ENTERED_INVALID_DATA +
                "[login: " + login + ", password: " + password + ", username: " + userName + ", userSurname: " + userSurname + "]");
        return false;
    }

    /**
     * Returns user by [login, password]
     *
     * @param login    String
     * @param password String
     * @return UserModel
     */
    @Override
    public UserModel getUserByLoginAndPassword(String login, String password) {
        if (!login.equals("") && !password.equals("")) {
            UserModel user = this.userDAO.getUserByLoginAndPassword(login, password);
            if (user == null) {
                getException(ERROR_AUTHORIZATION_USER_IS_NOT_FOUND, ERROR_AUTHORIZATION_USER_IS_NOT_FOUND);
                return null;
            } else {
                return user;
            }
        }
        getException(ERROR_AUTHORIZATION_USER_ENTERED_INVALID_DATA, ERROR_AUTHORIZATION_USER_ENTERED_INVALID_DATA);
        return null;
    }

    /**
     * Returns user by [sessionId]
     *
     * @param sessionId int
     * @return UserModel
     */
    @Override
    public UserModel getUserBySessionId(int sessionId) {
        return this.userDAO.getUserBySessionId(sessionId);
    }

    /**
     * Returns user by [login]
     *
     * @param login String
     * @return UserModel
     */
    @Override
    public UserModel getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

    /**
     * Writes user List to file
     * void
     */
    @Override
    public void writeUserListToFile() {
        this.userDAO.writeUserListToFile();
    }

}